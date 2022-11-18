package com.bside.server.module.tmp.controller;

import com.bside.server.global.dto.ParamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 테스트용 임시 컨트롤러
 * */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TmpController {
    @GetMapping
    public String getTest(@ModelAttribute ParamDto paramDto) {
        return "ok";
    }
}
