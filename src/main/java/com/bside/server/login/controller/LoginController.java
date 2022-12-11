package com.bside.server.login.controller;

import com.bside.server.login.domain.AuthModel;
import com.bside.server.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public AuthModel login(HttpServletRequest request) throws Exception {
    return loginService.login(request);
  }
}