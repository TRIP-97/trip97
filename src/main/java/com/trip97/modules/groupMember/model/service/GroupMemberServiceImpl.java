package com.trip97.modules.groupMember.model.service;

import com.trip97.modules.groupMember.model.GroupMember;
import com.trip97.modules.groupMember.model.GroupMemberStatus;
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
        GroupMember groupMember = GroupMember.builder()
                .groupId(groupId)
                .memberId(memberId)
                .status(GroupMemberStatus.WAITING)
                .build();
        return groupMemberMapper.insertGroupMember(groupMember);
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
}
