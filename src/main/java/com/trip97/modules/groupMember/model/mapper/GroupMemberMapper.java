package com.trip97.modules.groupMember.model.mapper;

import com.trip97.modules.groupMember.model.GroupMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface GroupMemberMapper {

    Integer insertGroupMember(GroupMember groupMember);
    List<GroupMember> selectWaitingGroupMemberByGroupId(Integer groupId);
    List<GroupMember> selectAcceptedGroupMemberByGroupId(Integer groupId);
    Integer acceptGroupMember(HashMap<String, Integer> map);
    Integer deleteGroupMember(HashMap<String, Integer> map);
}
