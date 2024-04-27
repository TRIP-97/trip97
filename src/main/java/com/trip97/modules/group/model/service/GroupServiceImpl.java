package com.trip97.modules.group.model.service;

import com.trip97.modules.group.model.Group;
import com.trip97.modules.group.model.mapper.GroupMapper;
import com.trip97.modules.groupMember.model.GroupMember;
import com.trip97.modules.groupMember.model.GroupMemberStatus;
import com.trip97.modules.groupMember.model.mapper.GroupMemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;
    private final GroupMemberMapper groupMemberMapper;

    @Override
    public Integer createGroup(Group group) {
        groupMapper.insertGroup(group);
        System.out.println(group.getId());
        GroupMember groupMember = GroupMember.builder()
                .groupId(group.getId())
                .memberId(group.getCreatorId())
                .status(GroupMemberStatus.ACCEPT)
                .build();
        return groupMemberMapper.insertGroupMember(groupMember);
    }

    @Override
    public List<Group> selectGroups(Integer memberId) {
        return groupMapper.selectGroupsByMemberId(memberId);
    }

    @Override
    public Group selectGroup(Integer groupId) {
        return groupMapper.selectGroupById(groupId);
    }

    @Override
    public Integer editGroup(Group group) {
        return groupMapper.updateGroup(group);
    }

    @Override
    public Integer removeGroup(Integer groupId) {
        return groupMapper.deleteGroupById(groupId);
    }
}
