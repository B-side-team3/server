package com.bside.server.global.auth.security;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.member.dto.MemberDto;
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

    public String createToken(MemberDto memberDto) {
        return doCreateToken(memberDto, tokenExpiry);
    }

    public String createRefreshToken(MemberDto memberDto) {
        return doCreateToken(memberDto, refreshTokenExpiry);
    }

    public String doCreateToken(MemberDto memberDto, long expireTime)  {
        Claims claims = Jwts.claims();
        claims.put("email", memberDto.getEmail());

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expireTime))
            .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
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
                throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
        }
    }

// todo 만료된 access 토큰 refresh 처리
//    private boolean needRefresh(String key, int createTime) {
//    }

}
