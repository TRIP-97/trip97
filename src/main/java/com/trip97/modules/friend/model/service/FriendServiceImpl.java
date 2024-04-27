package com.trip97.modules.friend.model.service;

import com.trip97.modules.friend.model.FriendInfo;
import com.trip97.modules.friend.model.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendMapper friendMapper;

    @Override
    public void removeFriend(int toId, int fromId) {
        Map<String, Integer> myFriend = new HashMap<>();
        Map<String, Integer> counterFriend = new HashMap<>();
        myFriend.put("toId", toId);
        myFriend.put("fromId", fromId);
        counterFriend.put("toId", fromId);
        counterFriend.put("fromId", toId);

        friendMapper.deleteFriend(myFriend);
        friendMapper.deleteFriend(counterFriend);
    }

    @Override
    public List<FriendInfo> searchFriends(int id) {
        return friendMapper.selectFriends(id);
    }
}
