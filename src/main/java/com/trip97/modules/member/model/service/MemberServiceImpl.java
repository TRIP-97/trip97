package com.trip97.modules.member.model.service;

import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.ProfileImageDto;
import com.trip97.modules.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements  MemberService {

    private final MemberMapper memberMapper;

    @Override
    public Optional<Member> getMemberById(int id) {
        Optional<Member> member = memberMapper.selectMemberById(id);
        ProfileImageDto profileImageDto = memberMapper.getFileInfo(id);
        member.get().setProfileImage(profileImageDto.getUrl());
        return member;
    }

    @Override
    public Optional<Member> getMemberByFriendCode(String friendCode) {
        return memberMapper.selectMemberByFriendCode(friendCode);
    }

    @Override
    public Integer editMember(Integer memberId, Member member, ProfileImageDto profileImageDto) {
        Member updateMember;
        if (profileImageDto != null) {
            updateMember = Member.builder()
                .id(memberId)
                .nickname(member.getNickname())
                .profileImage(profileImageDto.getUrl())
                .introduction(member.getIntroduction())
                .build();
            memberMapper.updateFile(profileImageDto);
        } else {
            updateMember = Member.builder()
                .id(memberId)
                .nickname(member.getNickname())
                .profileImage(member.getProfileImage())
                .introduction(member.getIntroduction())
                .build();
        }
        return memberMapper.updateMember(updateMember);
    }
}
