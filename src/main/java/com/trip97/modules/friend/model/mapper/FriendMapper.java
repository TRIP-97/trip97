package com.trip97.modules.friend.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.trip97.modules.friend.model.Friend;

@Mapper
public interface FriendMapper {

	Integer insertFriend(Friend friend);
}
