package com.trip97.modules.friendship.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trip97.modules.friendship.model.WaitingFriendship;
import com.trip97.modules.friendship.model.service.FriendshipService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/friendship")
@RequiredArgsConstructor
public class FriendshipController {

	private final FriendshipService friendshipService;
	
	@PostMapping
	public ResponseEntity<?> sendFriendshipRequest(@RequestParam("toUserId") Integer toUserId, @RequestParam("fromUserId") Integer fromUserId) throws Exception {
		friendshipService.createFriendship(fromUserId, toUserId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping
	public ResponseEntity<?> getWaitingFriendShips(@RequestParam("memberId") Integer memberId) throws Exception {
		List<WaitingFriendship> list = friendshipService.getWaitingFriendships(memberId);
		if (list != null && !list.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> approveFriendship(@PathVariable("id") Integer friendshipId) throws Exception {
		friendshipService.approveFriendshipRequest(friendshipId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> refuseFriendship(@PathVariable("id") Integer friendshipId) throws Exception {
		friendshipService.refuseFriendshipRequest(friendshipId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
