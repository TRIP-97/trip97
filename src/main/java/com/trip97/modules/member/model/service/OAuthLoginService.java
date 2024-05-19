package com.trip97.modules.member.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.trip97.modules.member.model.ProfileImageDto;
import com.trip97.modules.member.model.Role;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.trip97.modules.friendship.model.mapper.FriendshipMapper;
import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.jwt.AuthTokens;
import com.trip97.modules.member.model.jwt.AuthTokensGenerator;
import com.trip97.modules.member.model.mapper.MemberMapper;
import com.trip97.modules.member.model.oauth.OAuthInfoResponse;
import com.trip97.modules.member.model.oauth.OAuthLoginParams;
import com.trip97.modules.member.model.oauth.RequestOAuthInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    @Value("${file.path}")
    private String uploadPath;

    @Value("${local.domain}")
    private String localDomain;

    private final MemberMapper memberMapper;
    private final FriendshipMapper friendshipMapper;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Integer memberId = findOrCreateMember(oAuthInfoResponse);
        log.info("findOrCreateMember memberId:{}", memberId);
        return authTokensGenerator.generate(memberId);
    }

    private Integer findOrCreateMember(OAuthInfoResponse oAuthInfoResponse)  {
        return memberMapper.selectMemberByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> {
                    try {
                        return newMember(oAuthInfoResponse);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private int newMember(OAuthInfoResponse oAuthInfoResponse) throws IOException {
    	String randomFriendCode = makeFriendCode();
    	
    	Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .friendCode(randomFriendCode)
                .role(Role.USER)
                .build();
    	memberMapper.insertMember(member);
        ProfileImageDto profileImageDto = processImageFiles(member);
        memberMapper.registerFile(profileImageDto);

        member.setProfileImage(profileImageDto.getUrl());
        memberMapper.updateMember(member);
        return member.getId();
    }

    private ProfileImageDto processImageFiles(Member member) throws IOException {
        ProfileImageDto profileImageDto = getDefaultImage(member);
        return profileImageDto;
    }

    private ProfileImageDto getDefaultImage(Member member) throws IOException {
        // 정적 리소스 경로
        File defaultFolder = new File(uploadPath + File.separator + "member" + File.separator + "defaultImages");
        File[] files = defaultFolder.listFiles();
        log.info("defaultFolder:", defaultFolder);
        log.info("files:", files);

        if (files != null && files.length > 0) {
            File selectedFile = files[new Random().nextInt(files.length)];
            String url = localDomain + "/images/member/defaultImages/" + selectedFile.getName(); // 웹 접근 경로

            ProfileImageDto fileInfoDto = new ProfileImageDto();
            fileInfoDto.setMemberId(member.getId());
            fileInfoDto.setSaveFolder("images");
            fileInfoDto.setOriginalFile(selectedFile.getName());
            fileInfoDto.setSaveFile(selectedFile.getName());
            fileInfoDto.setUrl(url);

            return fileInfoDto;
        }
        return null;
    }

    private String makeFriendCode() {
    	while (true) {
    		String randomCode = RandomStringUtils.random(10, true, true);
    		if (friendshipMapper.findCode(randomCode) == null) {
    			log.debug("result: {}", friendshipMapper.findCode(randomCode));
    			return randomCode;
    		}
    	}
    }
    
}
