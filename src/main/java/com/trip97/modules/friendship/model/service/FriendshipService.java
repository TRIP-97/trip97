package com.trip97.modules.friendship.model.service;

import java.util.List;

import com.trip97.modules.friendship.model.WaitingFriendship;

public interface FriendshipService {

	void createFriendship(int fromId, int toId) throws Exception;
	List<WaitingFriendship> getWaitingFriendships(int memberId) throws Exception;
	void approveFriendshipRequest(Integer friendshipId) throws Exception;
	void refuseFriendshipRequest(Integer friendshipId) throws Exception;
	boolean isMemberInFriendships(int memberId) throws Exception;
}
