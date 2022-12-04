package com.bside.server.global.auth.security;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.global.util.profile.ActiveProfile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtValidator {

    @Value("${spring.jwt.secret}")
    private String secretKey;
    @Value("${spring.jwt.tokenExpiry}")
    private String tokenExpiry;
    @Value("${spring.jwt.refreshTokenExpiry}")
    private String refreshTokenExpiry;

    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        Claims claims = getClaim(token);

        // todo token payload 에 따라 변경 예정
        String email = claims.get("email", String.class);
        Integer expiration = claims.get("exp", Integer.class);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);

        return authentication;
    }

    public String createToken(String key) {
        Claims claims = Jwts.claims().setId(key);
        Date now = new Date();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenExpiry))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    private String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null) {
            throw new AuthenticationException(ErrorCode.AUTHORIZATION_HEADER_NOT_FOUND);
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationException(ErrorCode.INVALID_AUTHENTICATION_TYPE);
        }

        return authorizationHeader.substring("Bearer ".length()).trim();
    }

    private Claims getClaim(String token) throws ExpiredJwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {

            if (ActiveProfile.isDev()) {
                return e.getClaims();
            } else {
                throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
            }
        }
    }

// todo 만료된 access 토큰 refresh 처리
//    private boolean needRefresh(String key, int createTime) {
//    }

}