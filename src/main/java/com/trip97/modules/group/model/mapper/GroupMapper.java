package com.trip97.modules.group.model.mapper;

import com.trip97.modules.group.model.Group;
import com.trip97.modules.group.model.GroupFileInfoDto;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GroupMapper {

    Integer insertGroup(Group group);
    void registerFile(Group group);
    List<Group> selectGroups(Map<String, Object> param);
    List<Group> selectGroupsByMemberId(Map<String, Object> param);
    Integer getTotalGroupCount(Map<String, Object> param);
    Group selectGroupById(Integer groupId);
    Integer updateGroup(Group group);
    Integer deleteGroupById(Integer groupId);
    List<GroupFileInfoDto> fileInfoList(int groupId);
    GroupFileInfoDto getFileInfo(int groupId);
}
