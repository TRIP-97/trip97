package com.trip97.modules.member.model.mapper;

import java.util.List;
import java.util.Optional;

import com.trip97.modules.hotPlace.model.FileInfoDto;
import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.member.model.ProfileImageDto;
import org.apache.ibatis.annotations.Mapper;

import com.trip97.modules.member.model.Member;

@Mapper
public interface MemberMapper {

    Integer insertMember(Member member);
    Optional<Member> selectMemberByEmail(String email);
    Optional<Member> selectMemberById(int id);
    Optional<Member> selectMemberByFriendCode(String friendCode);
    Optional<Member> selectMemberByRefreshToken(String refreshToken);
    Integer updateMember(Member member);
    void registerFile(ProfileImageDto profileImageDto);
    void updateFile(ProfileImageDto profileImageDto);
    ProfileImageDto getFileInfo(int memberId);
}
