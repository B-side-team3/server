package com.bside.server.module.auth.controller;

import com.bside.server.global.util.UserContext;
import com.bside.server.module.auth.dto.TokenResponse;
import com.bside.server.module.auth.service.AuthService;
import com.bside.server.module.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;

    @PostMapping("/token")
    public TokenResponse login(HttpServletRequest request) {
      Member member = authService.getMember(request);
      return authService.upsertOauth(member);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(HttpServletRequest request) {
        authService.validateRefresh(request);
        return authService.upsertOauth(UserContext.getMember());
    }

    @DeleteMapping("/revoke")
    public void revoke() {
        authService.revoke();
    }
}
