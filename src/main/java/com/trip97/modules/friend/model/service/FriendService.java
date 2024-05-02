package com.trip97.modules.friend.model.service;

import com.trip97.modules.friend.model.FriendInfo;

import java.util.List;

public interface FriendService {

    void removeFriend(int toId, int fromId);
    List<FriendInfo> searchFriends(int id);
}
