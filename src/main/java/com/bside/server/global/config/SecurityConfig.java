package com.bside.server.global.config;

import com.bside.server.global.auth.security.AuthenticationFilter;
import com.bside.server.global.auth.security.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtValidator jwtValidator;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // corsFilter 활성화
                .cors()
                .and()
                // REST API 사용하여 비활성화
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                // 세션 관리 안함
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 인증 절차 설정
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").authenticated()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .and()
                .logout()
                //.logoutUrl() todo 로그아웃 url 지정
                //.logoutSuccessUrl() todo 로그아웃 성공 시 redirect 될 url 지정
            ;

        http.addFilterBefore(new AuthenticationFilter(jwtValidator), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            .mvcMatchers(HttpMethod.GET, "/docs/index.html")
            .mvcMatchers(HttpMethod.POST, "/auth/token")
            .mvcMatchers("/v3/api-docs", "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // todo prod, dev 두 포트 열어야 한다.
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://106.10.51.81:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
