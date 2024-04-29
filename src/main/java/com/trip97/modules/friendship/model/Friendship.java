package com.trip97.modules.friendship.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {

	private Integer id;
	private Integer memberId;
	private Integer counterpartId;
	private FriendshipStatus status;
	private Boolean isFrom;
	
	public void acceptFriendshipRequest() {
		status = FriendshipStatus.ACCEPT;
	}
	
	public void setCounterpartID(Integer id) {
		counterpartId = id;
	}
}
