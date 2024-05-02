package com.trip97.modules.member.model.service;

import com.trip97.modules.member.model.Member;
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
        return memberMapper.selectMemberById(id);
    }

    @Override
    public Integer editMember(Integer memberId, Member member) {
        Member updateMember = Member.builder()
                .id(memberId)
                .nickname(member.getNickname())
                .profileImage(member.getProfileImage())
                .introduction(member.getIntroduction())
                .build();
        return memberMapper.updateMember(updateMember);
    }
}
