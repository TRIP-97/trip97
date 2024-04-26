package com.trip97.modules.member.model.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.trip97.modules.member.model.Member;

@Mapper
public interface MemberMapper {

    Optional<Member> findByEmail(String email);
    Optional<Member> findById(int id);
    Integer save(Member member);
    List<Member> findAll();
}
