package com.course2go.service.user;

import java.util.List;
import java.util.Optional;

import com.course2go.model.user.UserProfileResponse;
import com.course2go.service.follow.FollowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.FollowDao;
import com.course2go.dao.NoticeDao;
import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private FollowDao followDao;
	
	@Autowired
	private FollowListService followListService;
	
	@Override
	public Object userProfileShow(String tokenNickname, String userNickname) {
		
		Optional<User> requestUser = userDao.findUserByUserNickname(tokenNickname);
		Optional<User> targetUser = userDao.findUserByUserNickname(tokenNickname);
		
		
		if(requestUser.isPresent()) { // 프로필 보기 요청한 유저가 존재하는 경우
			if(targetUser.isPresent()){ // 검색한 유저가 존재하는 경우
				
				Optional<User> result = userDao.findUserByUserNickname(userNickname);
				UserProfileResponse userProfileResponse = new UserProfileResponse();
				userProfileResponse.setUserName(result.get().getUserName());
				userProfileResponse.setUserNickname(result.get().getUserNickname());
				userProfileResponse.setUserImage(result.get().getUserImage());
				userProfileResponse.setUserComment(result.get().getUserComment());
				userProfileResponse.setUserFollower(followListService.getFollowerCount(userNickname));
				userProfileResponse.setUserFollowing(followListService.getFollowingCount(userNickname));
				
				
				// Follow하고 있는 경우 followState 를 1로 설정
				if(followDao.existsByFollowFromUidAndFollowToUid(requestUser.get().getUid(), targetUser.get().getUid())) {
					userProfileResponse.setFollowState(1);
				}
				
				// Follow하고 있는 경우 followState 를 2로 설정
				if(noticeDao.existsByNoticeTypeAndNoticeUidAndNoticeFromUid(1, targetUser.get().getUid(), requestUser.get().getUid())) {
					userProfileResponse.setFollowState(2);
				}
				
				return userProfileResponse;
			}else{ // 검색한 유저가 존재하지 않는 유저인 경우
				return "nonexistent user";
			}
		} else return "fail";
		
	}

}
