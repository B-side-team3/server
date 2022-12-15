package com.bside.server.module.login.controller;

import com.bside.server.module.login.dto.AuthDto;
import com.bside.server.module.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
  public AuthDto login(HttpServletRequest request) throws Exception {
    return loginService.login(request);
  }

  @PostMapping("/logout")
  public String logout(HttpServletRequest request) throws Exception {
    loginService.logout(request);
    return "redirect:/";
  }
}
