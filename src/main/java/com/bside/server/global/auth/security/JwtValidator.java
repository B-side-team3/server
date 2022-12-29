package com.bside.server.global.auth.security;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.module.auth.dto.TokenResponse;
import com.bside.server.module.member.dto.MemberDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtValidator {

    @Value("${spring.jwt.secret}")
    private String secretKey;
    @Value("${spring.jwt.tokenExpiry}")
    private Long tokenExpiry;
    @Value("${spring.jwt.refreshTokenExpiry}")
    private Long refreshTokenExpiry;

    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private Key getSignInKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        Claims claims = getClaim(token);

        // todo token payload 에 따라 변경 예정
        String email = claims.get("email", String.class);
        Integer expiration = claims.get("exp", Integer.class);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, null);
    }

    public TokenResponse createToken(String email) {

        Claims claims = Jwts.claims();
        claims.put("email", email);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiry))
                .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiry))
                .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .compact();

        return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getClaim(token).getExpiration();
        return expiration.before(new Date());
    }



    public String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null) {
            throw new AuthenticationException(ErrorCode.AUTHORIZATION_HEADER_NOT_FOUND);
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationException(ErrorCode.INVALID_AUTHENTICATION_TYPE);
        }

        return authorizationHeader.substring("Bearer ".length()).trim();
    }

    private Claims getClaim(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN); // todo 추후 주석 해제
            return e.getClaims();   // 개발할 때는 만료된 토큰도 이용 가능하도록 처리

        }
    }

// todo 만료된 access 토큰 refresh 처리
//    private boolean needRefresh(String key, int createTime) {
//    }

}
