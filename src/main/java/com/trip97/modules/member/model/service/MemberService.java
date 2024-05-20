package com.trip97.modules.member.model.service;

import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.ProfileImageDto;

import java.util.Optional;

public interface MemberService {

    Optional<Member> getMemberById(int id);
    Optional<Member> getMemberByFriendCode(String friendCode);
    Integer editMember(Integer memberId, Member member, ProfileImageDto profileImageDto);
}
