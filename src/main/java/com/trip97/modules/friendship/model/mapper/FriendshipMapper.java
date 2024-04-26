package com.trip97.modules.friendship.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendshipMapper {
	
	Integer findCode(String code);
}
