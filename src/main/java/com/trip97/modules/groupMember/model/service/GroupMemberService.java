package com.trip97.modules.groupMember.model.service;

import com.trip97.modules.groupMember.model.GroupMember;

import java.util.List;

public interface GroupMemberService {

    Integer requestGroupMember(int groupId, int memberId);
    Integer findGroupMember(int groupId, int memberId);
    List<GroupMember> getWaitingGroupMemberByGroupId(Integer groupId);
    List<GroupMember> getAcceptedGroupMemberByGroupId(Integer groupId);
    Integer acceptGroupMember(Integer groupId, Integer groupMemberId);
    Integer removeGroupMember(Integer groupId, Integer groupMemberId);
}
