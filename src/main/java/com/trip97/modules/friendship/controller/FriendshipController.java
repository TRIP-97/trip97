package com.trip97.modules.friendship.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trip97.modules.friendship.model.WaitingFriendship;
import com.trip97.modules.friendship.model.service.FriendshipService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {

	private final FriendshipService friendshipService;
	
	public FriendshipController(FriendshipService friendshipService) {
		this.friendshipService = friendshipService;
	}

	@PostMapping
	public ResponseEntity<?> sendFriendshipRequest(@RequestParam("toUserId") Integer toUserId, @RequestParam("fromUserId") Integer fromUserId) throws Exception {
		friendshipService.createFriendship(fromUserId, toUserId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/{member-id}")
	public ResponseEntity<?> getWaitingFriendShips(@PathVariable("member-id") Integer memberId) throws Exception {
		List<WaitingFriendship> list = friendshipService.getWaitingFriendships(memberId);
		if (list != null && !list.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> approveFriendship(@PathVariable("id") Integer friendshipId,
			@RequestParam("memberId") int memberId) throws Exception {
		friendshipService.approveFriendshipRequest(friendshipId);
		List<WaitingFriendship> list = friendshipService.getWaitingFriendships(memberId);
		if (list != null && !list.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
}
