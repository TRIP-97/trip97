package com.trip97.modules.groupMember.model.mapper;

import com.trip97.modules.groupMember.model.GroupMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface GroupMemberMapper {

    Integer insertGroupMember(GroupMember groupMember);
    int countMatchingMembers(Map<String, Object> params);
    List<GroupMember> selectWaitingGroupMemberByGroupId(Integer groupId);
    List<GroupMember> selectAcceptedGroupMemberByGroupId(Integer groupId);
    Integer acceptGroupMember(HashMap<String, Integer> map);
    Integer deleteGroupMember(HashMap<String, Integer> map);
}
