package com.bside.server.global.config;

import com.bside.server.global.auth.security.AuthenticationFilter;
import com.bside.server.global.auth.security.TokenAuthenticationFilter;
import com.bside.server.global.auth.token.AuthTokenProvider;
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

    private final AuthenticationFilter authenticationFilter;
    private final AuthTokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // corsFilter 활성화
                .cors()
                .and()
                // csrf 비활성화
                .csrf().disable()
                // 세션 관리 안함
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 인증 절차 설정
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                 .disable() // todo 로그인 구현 시 주석된 부분 설정
//                .usernameParameter("") // 로그인 폼의 input 태그 name
//                .passwordParameter("") // 로그인 폼의 password 태그의 name
//                .loginProcessingUrl("/authenticate")
//                .permitAll()
//                .and()
                .logout()
                //.logoutUrl() todo 로그아웃 url 지정
                //.logoutSuccessUrl() todo 로그아웃 성공 시 redirect 될 url 지정
            ;

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .mvcMatchers(HttpMethod.GET, "/docs/index.html")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
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
