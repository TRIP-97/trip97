package com.trip97.modules.group.model.service;

import com.trip97.modules.group.model.Group;
import com.trip97.modules.group.model.GroupListDto;

import java.util.Map;

public interface GroupService {

    Integer createGroup(Group group);
    GroupListDto selectGroups(Map<String, String> map);
    GroupListDto selectGroupsByMemberId(Map<String, String> map);
    Group selectGroup(Integer groupId);
    Integer editGroup(Group group);
    Integer removeGroup(Integer groupId);
}
