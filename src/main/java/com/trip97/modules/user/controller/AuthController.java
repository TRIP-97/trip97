package com.trip97.modules.user.controller;

import com.trip97.modules.user.model.User;
import com.trip97.modules.user.model.jwt.AuthTokens;
import com.trip97.modules.user.model.oauth.NaverLoginParams;
import com.trip97.modules.user.model.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<User>> findUsers() {
        return ResponseEntity.ok(oAuthLoginService.findUsers());
    }
}
