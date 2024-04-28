package com.trip97.modules.member.model.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.trip97.modules.member.model.Member;

@Mapper
public interface MemberMapper {

    Integer insertMember(Member member);
    Optional<Member> selectMemberByEmail(String email);
    Optional<Member> selectMemberById(int id);
    Integer updateMember(Member member);
}
