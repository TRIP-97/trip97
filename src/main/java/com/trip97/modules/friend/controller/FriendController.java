package com.trip97.modules.friend.controller;

import com.trip97.modules.friend.model.FriendInfo;
import com.trip97.modules.friend.model.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchFriend(@PathVariable("id") Integer id) {
        List<FriendInfo> list = friendService.searchFriends(id);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFriend(@PathVariable("id") int toId , @RequestParam("fromId") Integer fromId) {
        friendService.removeFriend(toId, fromId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
