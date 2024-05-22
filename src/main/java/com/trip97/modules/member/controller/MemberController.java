package com.trip97.modules.member.controller;

import com.trip97.modules.hotPlace.model.FileInfoDto;
import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.ProfileImageDto;
import com.trip97.modules.member.model.jwt.AuthTokens;
import com.trip97.modules.member.model.jwt.AuthTokensGenerator;
import com.trip97.modules.member.model.oauth.KakaoLoginParams;
import com.trip97.modules.member.model.oauth.NaverLoginParams;
import com.trip97.modules.member.model.service.MemberService;
import com.trip97.modules.member.model.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController {

    @Value("${file.path}")
    private String uploadPath;

    @Value("${local.domain}")
    private String localDomain;

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
        log.info("params: {}, {}", params.getAuthorizationCode(), params.getState());
        AuthTokens token = oAuthLoginService.login(params);
        log.info("token: {}", token.getAccessToken());
        return ResponseEntity.ok(token);
    }

    /**
     * 카카오 로그인
     */

    @PostMapping("/login/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params){
        log.info("params: {}, {}", params.getAuthorizationCode());
        AuthTokens token = oAuthLoginService.login(params);
        log.info("token: {}", token.getAccessToken());
        return ResponseEntity.ok(token);
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
        log.info("token: {}", token);
        Integer memberId = authTokensGenerator.extractMemberId(token);
        Optional<Member> member = memberService.getMemberById(memberId);
        log.info("memberId: {}, member: {}", memberId, member);
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
     */
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateMember(@RequestHeader("Authorization") String token, @RequestPart("member") Member member, @RequestPart(value = "upfile", required = false) MultipartFile file) throws IOException {
        token = token.substring(7);
        Integer memberId = authTokensGenerator.extractMemberId(token);

        if (file != null) {
            ProfileImageDto profileImageDto = processImageFile(member, file);
            memberService.editMember(memberId, member, profileImageDto);
        } else {
            memberService.editMember(memberId, member, null);
        }

        Optional<Member> updatedMember = memberService.getMemberById(memberId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(updatedMember);
    }

    // 이미지 등록하기
    private ProfileImageDto processImageFile(Member member, MultipartFile file) throws IOException {
        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String saveFolder = uploadPath + File.separator + "member" + File.separator + "upload" + File.separator + today;
        File folder = new File(saveFolder);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        ProfileImageDto profileImageDto;
        if (file != null) {
            profileImageDto = processSingleFile(member.getId(), today, folder, file);
        } else {
            // 랜덤 이미지 선택 로직
            profileImageDto = getRandomDefaultImage(member.getId());
        }

        member.setProfileImage(profileImageDto.getUrl());
        return profileImageDto;
    }

    private ProfileImageDto processSingleFile(int memberId, String today, File folder, MultipartFile mfile) throws IOException {
        ProfileImageDto profileImageDto = new ProfileImageDto();
        String originalFileName = mfile.getOriginalFilename();

        if (!originalFileName.isEmpty()) {
            String saveFileName = UUID.randomUUID().toString()
                    + originalFileName.substring(originalFileName.lastIndexOf('.'));
            String url = localDomain + "/images/member/upload/" + today + "/" + saveFileName;
            profileImageDto.setMemberId(memberId);
            profileImageDto.setSaveFolder(today);
            profileImageDto.setOriginalFile(originalFileName);
            profileImageDto.setSaveFile(saveFileName);
            profileImageDto.setUrl(url);
            log.info("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
            mfile.transferTo(new File(folder, saveFileName));
        }

        return profileImageDto;
    }

    private ProfileImageDto getRandomDefaultImage(int memberId) throws IOException {
        // 정적 리소스 경로
        File defaultFolder = new File(uploadPath + File.separator + "member" + File.separator + "defaultImages");
        File[] files = defaultFolder.listFiles();
        log.info("defaultFolder:", defaultFolder);
        log.info("files:", files);

        if (files != null && files.length > 0) {
            File selectedFile = files[new Random().nextInt(files.length)];
            String url = localDomain + "/images/member/defaultImages/" + selectedFile.getName(); // 웹 접근 경로

            ProfileImageDto profileImageDto = new ProfileImageDto();
            profileImageDto.setMemberId(memberId);
            profileImageDto.setSaveFolder("images");
            profileImageDto.setOriginalFile(selectedFile.getName());
            profileImageDto.setSaveFile(selectedFile.getName());
            profileImageDto.setUrl(url);

            return profileImageDto;
        }
        return null;
    }

    /**
     * 친구 코드로 회원 조회
     */
    @GetMapping("/search/friendCode")
    public ResponseEntity<?> searchMemberByFriendCode(@RequestParam String friendCode) {
        Optional<Member> member = memberService.getMemberByFriendCode(friendCode);
        if (member.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(member);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}