package com.trip97.modules.groupMember.model.service;

import com.trip97.modules.groupMember.model.GroupMember;
import com.trip97.modules.groupMember.model.GroupMemberStatus;
import com.trip97.modules.groupMember.model.GroupRequest;
import com.trip97.modules.groupMember.model.mapper.GroupMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberMapper groupMemberMapper;

    @Override
    public Integer requestGroupMember(int groupId, int memberId) {
        GroupMember groupMember = new GroupMember(groupId, memberId, GroupMemberStatus.WAITING);
        return groupMemberMapper.insertGroupMember(groupMember);
    }

    @Override
    public Integer inviteGroupMember(int groupId, int memberId) {
        GroupMember groupMember = new GroupMember(groupId, memberId, GroupMemberStatus.WAITING_BY_FRIEND);
        return groupMemberMapper.insertGroupMember(groupMember);
    }

    @Override
    public Integer findGroupMember(int groupId, int memberId) {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        map.put("memberId", memberId);
        return groupMemberMapper.countMatchingMembers(map);
    }

    @Override
    public List<GroupMember> getWaitingGroupMemberByGroupId(Integer groupId) {
        return groupMemberMapper.selectWaitingGroupMemberByGroupId(groupId);
    }

    @Override
    public List<GroupMember> getAcceptedGroupMemberByGroupId(Integer groupId) {
        return groupMemberMapper.selectAcceptedGroupMemberByGroupId(groupId);
    }

    @Override
    public Integer acceptGroupMember(Integer groupId, Integer groupMemberId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("groupId", groupId);
        map.put("groupMemberId", groupMemberId);
        return groupMemberMapper.acceptGroupMember(map);
    }

    @Override
    public Integer removeGroupMember(Integer groupId, Integer groupMemberId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("groupId", groupId);
        map.put("groupMemberId", groupMemberId);
        return groupMemberMapper.deleteGroupMember(map);
    }

    @Override
    public List<GroupRequest> getWaitingGroupsForMember(Integer memberId) {
        return groupMemberMapper.selectWaitingGroupsForMember(memberId);
    }

    @Override
    public List<GroupRequest> getWaitingByFriendGroupsForMember(Integer memberId) {
        return groupMemberMapper.selectWaitingByFriendGroupsForMember(memberId);
    }
}
