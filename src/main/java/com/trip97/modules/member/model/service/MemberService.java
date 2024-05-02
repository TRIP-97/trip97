package com.trip97.modules.member.model.service;

import com.trip97.modules.member.model.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> getMemberById(int id);
    Integer editMember(Integer memberId, Member member);
}
