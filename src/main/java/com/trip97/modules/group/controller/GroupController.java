package com.trip97.modules.group.controller;

import com.trip97.modules.group.model.Group;
import com.trip97.modules.group.model.service.GroupService;
import com.trip97.modules.groupMember.model.GroupMember;
import com.trip97.modules.groupMember.model.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;

    @GetMapping
    public ResponseEntity<?> getGroups(@RequestParam int memberId) {
        List<Group> list = groupService.selectGroups(memberId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
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

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody Group group) {
        groupService.createGroup(group);
        Group createdGroup = groupService.selectGroup(group.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(createdGroup);
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
    public ResponseEntity<?> requestGroupMember(@PathVariable int groupId, @RequestParam int memberId) {
        groupMemberService.requestGroupMember(groupId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{groupId}/member/{memberId}")
    public ResponseEntity<?> acceptGroupMember(@PathVariable int groupId, @PathVariable int memberId) {
        groupMemberService.acceptGroupMember(groupId, memberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groupId}/member/{memberId}")
    public ResponseEntity<?> removeGroupMember(@PathVariable int groupId, @PathVariable int memberId) {
        groupMemberService.removeGroupMember(groupId, memberId);
        return ResponseEntity.noContent().build();
    }
}
