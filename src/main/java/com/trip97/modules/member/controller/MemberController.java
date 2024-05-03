package com.trip97.modules.member.controller;

import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.jwt.AuthTokens;
import com.trip97.modules.member.model.jwt.AuthTokensGenerator;
import com.trip97.modules.member.model.oauth.KakaoLoginParams;
import com.trip97.modules.member.model.oauth.NaverLoginParams;
import com.trip97.modules.member.model.service.MemberService;
import com.trip97.modules.member.model.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthTokensGenerator authTokensGenerator;
    private final OAuthLoginService oAuthLoginService;

    /**
     * 네이버 로그인
     * @param params
     * @return
     */
    @PostMapping("/login/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    /**
     * 카카오 로그인
     */

    @PostMapping("/login/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params){
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    /**
     * 회원 정보 조회
     * @param token
     * @return
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getMyInfo(@RequestHeader("Authorization") String token) {
        // "Bearer " 접두어를 제거
        token = token.substring(7);
        Integer memberId = authTokensGenerator.extractMemberId(token);
        Optional<Member> member = memberService.getMemberById(memberId);

        if (member != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(member);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    /**
     * 회원 정보 수정
     * @param token
     * @param member
     * @return
     */
    @PatchMapping
    public ResponseEntity<?> updateMember(@RequestHeader("Authorization") String token, @RequestBody Member member) {
        token = token.substring(7);
        Integer memberId = authTokensGenerator.extractMemberId(token);
        memberService.editMember(memberId, member);

        Optional<Member> updatedMember = memberService.getMemberById(memberId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(updatedMember);
    }
}