package com.trip97.modules.group.controller;

import com.trip97.modules.group.model.Group;
import com.trip97.modules.group.model.GroupFileInfoDto;
import com.trip97.modules.group.model.GroupListDto;
import com.trip97.modules.group.model.service.GroupService;
import com.trip97.modules.groupMember.model.GroupMember;
import com.trip97.modules.groupMember.model.GroupRequest;
import com.trip97.modules.groupMember.model.service.GroupMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;

    @Value("${file.path}")
    private String uploadPath;

    @Value("${local.domain}")
    private String localDomain;
    
    @GetMapping
    public ResponseEntity<?> getGroups(@RequestParam Map<String, String> map) {
        GroupListDto groupList = groupService.selectGroups(map);
        if (groupList != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(groupList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    
    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getGroupsByMemberId(@RequestParam Map<String, String> map) {
        GroupListDto groupList = groupService.selectGroupsByMemberId(map);
        if (groupList != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(groupList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroup(@PathVariable("id") int groupId) {
        Group group = groupService.selectGroup(groupId);
        if (group != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(group);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createGroup(@RequestPart("group") Group group, @RequestPart(value = "upfile", required = false) MultipartFile[] files) throws IOException {
    	 processImageFiles(group, files);

    	 groupService.createGroup(group);
    	 
         Group createdGroup = groupService.selectGroup(group.getId());;
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
         return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(createdGroup);
    }
    
    private void processImageFiles(Group group, MultipartFile[] files) throws IOException {
        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String saveFolder = uploadPath + File.separator + "group" + File.separator + "upload" + File.separator + today;
        File folder = new File(saveFolder);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        List<GroupFileInfoDto> fileInfos = new ArrayList<>();

        if (files != null && !files[0].isEmpty()) {
            // 파일들을 처리하는 기존 로직
            for (MultipartFile mfile : files) {
            	GroupFileInfoDto fileInfoDto = processSingleFile(today, folder, mfile);
                fileInfos.add(fileInfoDto);
            }
        } else {
            // 랜덤 이미지 선택 로직
        	GroupFileInfoDto fileInfoDto = getRandomDefaultImage();
            fileInfos.add(fileInfoDto);
        }

        group.setFileInfos(fileInfos);
    }

    private GroupFileInfoDto processSingleFile(String today, File folder, MultipartFile mfile) throws IOException {
    	GroupFileInfoDto fileInfoDto = new GroupFileInfoDto();
        String originalFileName = mfile.getOriginalFilename();

        if (!originalFileName.isEmpty()) {
            String saveFileName = UUID.randomUUID().toString()
                    + originalFileName.substring(originalFileName.lastIndexOf('.'));
            String url = localDomain + "/images/group/upload/" + today + "/" + saveFileName;
            fileInfoDto.setSaveFolder(today);
            fileInfoDto.setOriginalFile(originalFileName);
            fileInfoDto.setSaveFile(saveFileName);
            fileInfoDto.setUrl(url);
            log.info("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
            mfile.transferTo(new File(folder, saveFileName));
        }

        return fileInfoDto;
    }

    private GroupFileInfoDto getRandomDefaultImage() throws IOException {
        // 정적 리소스 경로
        File defaultFolder = new File(uploadPath + File.separator + "group" + File.separator + "randomImages");
        File[] files = defaultFolder.listFiles();
        log.info("defaultFolder: {}", defaultFolder);
        log.info("files: {}", files);

        if (files != null && files.length > 0) {
            File selectedFile = files[new Random().nextInt(files.length)];
            String url = localDomain + "/images/group/randomImages/" + selectedFile.getName(); // 웹 접근 경로

            GroupFileInfoDto fileInfoDto = new GroupFileInfoDto();
            fileInfoDto.setSaveFolder("images");
            fileInfoDto.setOriginalFile(selectedFile.getName());
            fileInfoDto.setSaveFile(selectedFile.getName());
            fileInfoDto.setUrl(url);

            return fileInfoDto;
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable("id") int groupId, @RequestBody Group group) {
        groupService.editGroup(group);
        Group updatedGroup = groupService.selectGroup(group.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(updatedGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeGroup(@PathVariable("id") int groupId) {
        groupService.removeGroup(groupId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{groupId}/member/{memberId}")
    public ResponseEntity<?> findMember(@PathVariable("groupId") int groupId, @PathVariable("memberId") int memberId) {
        Integer count = groupMemberService.findGroupMember(groupId, memberId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(count);
    }

    @GetMapping("/{groupId}/member/wait")
    public ResponseEntity<?> getWaitingGroupMemberByGroupId(@PathVariable int groupId) {
        List<GroupMember> list = groupMemberService.getWaitingGroupMemberByGroupId(groupId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{groupId}/member/accept")
    public ResponseEntity<?> getAcceptedGroupMemberByGroupId(@PathVariable int groupId) {
        List<GroupMember> list = groupMemberService.getAcceptedGroupMemberByGroupId(groupId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/{groupId}/member")
    public ResponseEntity<?> requestGroupMember(@PathVariable int groupId, @RequestBody Map<String, String> params) {
        groupMemberService.requestGroupMember(groupId, Integer.valueOf(params.get("memberId")));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{groupId}/member/{memberId}")
    public ResponseEntity<?> acceptGroupMember(@PathVariable int groupId, @PathVariable int memberId) {
        groupMemberService.acceptGroupMember(groupId, memberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groupId}/member/{memberId}")
    public ResponseEntity<?> removeGroupMember(@PathVariable int groupId, @PathVariable int memberId) {
        groupMemberService.removeGroupMember(groupId, memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/member/{memberId}/wait")
    public ResponseEntity<?> getWaitingGroupsForMember(@PathVariable int memberId) {
        List<GroupRequest> list = groupMemberService.getWaitingGroupsForMember(memberId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }
}
