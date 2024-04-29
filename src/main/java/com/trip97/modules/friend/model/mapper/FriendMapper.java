package com.trip97.modules.friend.model.mapper;

import com.trip97.modules.friend.model.FriendInfo;
import org.apache.ibatis.annotations.Mapper;

import com.trip97.modules.friend.model.Friend;

import java.util.List;
import java.util.Map;

@Mapper
public interface FriendMapper {

	Integer insertFriend(Friend friend);
	List<FriendInfo> selectFriends(int id);
	Integer deleteFriend(Map<String, Integer> map);
}
