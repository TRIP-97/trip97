package com.trip97.modules.group.model.mapper;

import com.trip97.modules.group.model.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    Integer insertGroup(Group group);
    List<Group> selectGroupsByMemberId(Integer memberId);
    Group selectGroupById(Integer groupId);
    Integer updateGroup(Group group);
    Integer deleteGroupById(Integer groupId);
}
