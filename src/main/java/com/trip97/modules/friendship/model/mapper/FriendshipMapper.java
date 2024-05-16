package com.trip97.modules.friendship.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.trip97.modules.friendship.model.Friendship;

@Mapper
public interface FriendshipMapper {
	
	Integer findCode(String code);
	Integer insertFriendship(Friendship friendship);
	List<Friendship> selectFriendships(int memberId);
	Friendship selectFriendshipById(int id);
	Friendship selectFriendshipByMemberIdAndCounterpartId(Map<String, Integer> map);
	Integer updateFriendshipStatus(Friendship friendship);
	Integer deleteFriendshipById(int id);
	Integer deleteFriendshipByDisconnect(Map<String, Integer> map);
	boolean checkIfMemberExistsInFriendships(int memberId);
}
