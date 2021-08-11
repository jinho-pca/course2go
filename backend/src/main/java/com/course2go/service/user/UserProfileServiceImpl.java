package com.course2go.service.user;

import java.util.List;
import java.util.Optional;

import com.course2go.model.user.UserProfileResponse;
import com.course2go.service.follow.FollowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private FollowListService followListService;
	
	@Override
	public Object userProfileShow(String tokenNickname, String userNickname) {
		
		if(userDao.findUserByUserNickname(tokenNickname).isPresent()) { // 프로필 보기 요청한 유저가 존재하는 경우
			if(userDao.findUserByUserNickname(userNickname).isPresent()){ // 검색한 유저가 존재하는 경우
				
				Optional<User> result = userDao.findUserByUserNickname(userNickname);
				UserProfileResponse userProfileResponse = new UserProfileResponse();
				userProfileResponse.setUserName(result.get().getUserName());
				userProfileResponse.setUserNickname(result.get().getUserNickname());
				userProfileResponse.setUserImage(result.get().getUserImage());
				userProfileResponse.setUserComment(result.get().getUserComment());
				userProfileResponse.setUserFollower(followListService.getFollowerCount(userNickname));
				userProfileResponse.setUserFollowing(followListService.getFollowingCount(userNickname));
				List<String> followingUidList = followListService.getFollowingListByNickname(tokenNickname); // 검색한 사용자의 팔로우 리스트
				
				if(followingUidList.contains(result.get().getUid())) {
					userProfileResponse.setFollowing(true);
				}
				
				return userProfileResponse;
			}else{ // 검색한 유저가 존재하지 않는 유저인 경우
				return "nonexistent user";
			}
		} else return "fail";
		
	}

}
