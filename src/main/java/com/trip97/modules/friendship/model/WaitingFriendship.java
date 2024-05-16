package com.trip97.modules.friendship.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WaitingFriendship {

	private Integer friendshipId;
	private String friendNickname;
	private String friendEmail;
	private String friendProfileImage;
	private String friendIntroduction;
	private FriendshipStatus status;
}
