package com.bside.server.module.auth.controller;

import com.bside.server.global.util.UserContext;
import com.bside.server.module.auth.dto.TokenResponse;
import com.bside.server.module.auth.service.AuthService;
import com.bside.server.module.member.domain.Member;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "token 발급 요청", notes = "카카오 accessToken 을 이용해 rolebit 토큰을 발급받는다.")
    @PostMapping("/token")
    public TokenResponse login(HttpServletRequest request) {
      Member member = authService.getMember(request);
      return authService.upsertOauth(member);
    }

    @ApiOperation(value = "token 재발급 요청", notes = "rolebit accessToken 을 이용해 토큰을 재발급받는다.")
    @PostMapping("/refresh")
    public TokenResponse refresh(HttpServletRequest request) {
        authService.validateRefresh(request);
        return authService.upsertOauth(UserContext.getMember());
    }

    @ApiOperation(value = "token 폐기", notes = "사용자의 토큰을 폐기한다.")
    @DeleteMapping("/revoke")
    public void revoke() {
        authService.revoke();
    }
}
