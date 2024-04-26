package com.trip97.modules.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.jwt.AuthTokens;
import com.trip97.modules.member.model.oauth.NaverLoginParams;
import com.trip97.modules.member.model.service.OAuthLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @GetMapping("/users")
    public ResponseEntity<List<Member>> findMembers() {
        return ResponseEntity.ok(oAuthLoginService.findMembers());
    }
}
