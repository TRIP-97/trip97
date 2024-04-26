package com.trip97.modules.friend.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Friend {

	private Integer id;
	private Integer memberId;
	private Integer counterpartId;
}
