package com.trip97.modules.group.model.service;

import com.trip97.modules.group.model.Group;
import com.trip97.modules.group.model.GroupFileInfoDto;
import com.trip97.modules.group.model.GroupListDto;
import com.trip97.modules.group.model.mapper.GroupMapper;
import com.trip97.modules.groupMember.model.GroupMember;
import com.trip97.modules.groupMember.model.GroupMemberStatus;
import com.trip97.modules.groupMember.model.mapper.GroupMemberMapper;
import com.trip97.modules.hotPlace.model.FileInfoDto;
import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.hotPlace.model.HotPlaceListDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public GroupListDto selectGroups(Map<String, String> map) {
    	Map<String, Object> param = new HashMap<>();

        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "12" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);

        List<Group> list = groupMapper.selectGroups(param);
       
        for (Group group : list) {
            GroupFileInfoDto fileInfoDto = groupMapper.getFileInfo(group.getId());
            if (fileInfoDto != null) {
                List<GroupFileInfoDto> files = new ArrayList<>();
                files.add(fileInfoDto);
                group.setFileInfos(files);
            }
        }

        int totalArticleCount = groupMapper.getTotalGroupCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        GroupListDto groupListDto = new GroupListDto();
        groupListDto.setGroups(list);
        groupListDto.setCurrentPage(currentPage);
        groupListDto.setTotalPageCount(totalPageCount);

        return groupListDto;
    }

    @Override
    public GroupListDto selectGroupsByMemberId(Map<String, String> map) {
    	Map<String, Object> param = new HashMap<>();

        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "12" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);
        param.put("memberId", map.get("memberId"));

        String key = map.get("key");
        param.put("key", key == null ? "" : key);

        List<Group> list = groupMapper.selectGroups(param);
       
        for (Group group : list) {
            GroupFileInfoDto fileInfoDto = groupMapper.getFileInfo(group.getId());
            if (fileInfoDto != null) {
                List<GroupFileInfoDto> files = new ArrayList<>();
                files.add(fileInfoDto);
                group.setFileInfos(files);
            }
        }
        
        int totalArticleCount = groupMapper.getTotalGroupCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        GroupListDto groupListDto = new GroupListDto();
        groupListDto.setGroups(list);
        groupListDto.setCurrentPage(currentPage);
        groupListDto.setTotalPageCount(totalPageCount);

        return groupListDto;
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
