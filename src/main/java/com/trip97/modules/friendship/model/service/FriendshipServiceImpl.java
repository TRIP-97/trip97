package com.trip97.modules.friendship.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip97.modules.friend.model.Friend;
import com.trip97.modules.friend.model.mapper.FriendMapper;
import com.trip97.modules.friendship.model.Friendship;
import com.trip97.modules.friendship.model.FriendshipStatus;
import com.trip97.modules.friendship.model.WaitingFriendship;
import com.trip97.modules.friendship.model.mapper.FriendshipMapper;
import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {

	private final MemberMapper memberMapper;
	private final FriendshipMapper friendshipMapper;
	private final FriendMapper friendMapper;
	
	/**
	 * 친구 요청을 보내는 메서드
	 */
	@Override
	@Transactional
	public void createFriendship(int fromId, int toId) throws Exception {
		
		// 현재 로그인되어있는 멤버 (요청 보내는 멤버)
		// String fromId = SecurityUtil.getLoginId();
		
		// 받는 멤버측에 저장될 친구 요청
		Friendship friendshipFrom = Friendship.builder()
				.memberId(fromId)
				.counterpartId(toId)
				.status(FriendshipStatus.WAITING)
				.isFrom(true)
				.build();
		
		// 보내는 멤버측에 저장될 친구 요청
		Friendship friendshipTo = Friendship.builder()
				.memberId(toId)
				.counterpartId(fromId)
				.status(FriendshipStatus.WAITING)
				.isFrom(false)
				.build();
		
		// 친구 요청 저장
		friendshipMapper.insertFriendship(friendshipTo);
		friendshipMapper.insertFriendship(friendshipFrom);
	}

	/**
	 *	받은 친구 요청 중, 대기중인 요청을 조회하는 메서드 
	 */
	@Override
	@Transactional
	public List<WaitingFriendship> getWaitingFriendships(int memberId) throws Exception {
		// 현재 로그인한 유저의 정보를 불러온다
		// Users users = usersRepository.findByEmail(SecurityUtil.getLoginEmail()).orElseThrow(() -> new Exception("회원 조회 실패"));
		List<Friendship> friendships = friendshipMapper.selectFriendships(memberId);
		
		// 조회된 결과 객체를 담을 리스트
		List<WaitingFriendship> searchResults = new ArrayList<>();
		
		for (Friendship fs : friendships) {
			// 보낸 요청이 아니고, 수락 대기중인 요청만 조회
			if (!fs.getIsFrom() && fs.getStatus() == FriendshipStatus.WAITING) {
				Member waitingFriend = memberMapper.selectMemberById(fs.getCounterpartId()).orElseThrow(() -> new Exception("회원 조회 실패"));
				WaitingFriendship wfs = WaitingFriendship.builder()
						.friendshipId(fs.getId())
						.friendEmail(waitingFriend.getEmail())
						.friendNickname(waitingFriend.getNickname())
						.friendProfileImage(waitingFriend.getProfileImage())
						.friendIntroduction(waitingFriend.getIntroduction())
						.status(fs.getStatus())
						.build();
				searchResults.add(wfs);
			}
		}
		
		return searchResults;
	}
	
	/**
	 * 친구 요청을 수락하는 메서드
	 */
	@Transactional
	public void approveFriendshipRequest(Integer friendshipId) throws Exception {
		// 본인과 상대방의 친구 요청 가져오기
		Friendship friendship = friendshipMapper.selectFriendshipById(friendshipId);
		Map<String, Integer> map = new HashMap<>();
		map.put("memberId", friendship.getCounterpartId());
		map.put("counterpartId", friendship.getMemberId());
		Friendship counterFriendship = friendshipMapper.selectFriendshipByMemberIdAndCounterpartId(map);
		
		// 둘 다 상태를 ACCEPT로 변경하기
		friendship.acceptFriendshipRequest();
		counterFriendship.acceptFriendshipRequest();
		friendshipMapper.updateFriendshipStatus(friendship);
		friendshipMapper.updateFriendshipStatus(counterFriendship);
		
		// 친구 목록에 서로 추가하기
		Friend friend = Friend.builder()
				.memberId(friendship.getMemberId())
				.counterpartId(friendship.getCounterpartId())
				.build();
		Friend counterfriend = Friend.builder()
				.memberId(friendship.getCounterpartId())
				.counterpartId(friendship.getMemberId())
				.build();
		friendMapper.insertFriend(friend);
		friendMapper.insertFriend(counterfriend);
	}

	@Override
	public void refuseFriendshipRequest(Integer friendshipId) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		Friendship friendship = friendshipMapper.selectFriendshipById(friendshipId);
		map.put("counterpartId", friendship.getMemberId());
		map.put("memberId", friendship.getCounterpartId());
		Integer counterpartFriendshipId = friendshipMapper.selectFriendshipByMemberIdAndCounterpartId(map).getId();

		friendshipMapper.deleteFriendshipById(friendshipId);
		friendshipMapper.deleteFriendshipById(counterpartFriendshipId);
	}

	@Override
	public boolean isMemberInFriendships(int memberId) {
		return friendshipMapper.checkIfMemberExistsInFriendships(memberId);
	}
}
