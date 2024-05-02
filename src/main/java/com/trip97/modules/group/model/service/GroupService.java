package com.trip97.modules.group.model.service;

import com.trip97.modules.group.model.Group;

import java.util.List;

public interface GroupService {

    Integer createGroup(Group group);
    List<Group> selectGroups(Integer memberId);
    Group selectGroup(Integer groupId);
    Integer editGroup(Group group);
    Integer removeGroup(Integer groupId);
}
