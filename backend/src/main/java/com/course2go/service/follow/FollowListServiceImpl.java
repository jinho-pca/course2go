package com.course2go.service.follow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.FollowDao;
import com.course2go.dao.NoticeDao;
import com.course2go.dao.UserDao;
import com.course2go.model.follow.Follow;
import com.course2go.model.follow.MappedFollow;
import com.course2go.model.user.User;
import com.course2go.model.user.UserDto;

@Service
public class FollowListServiceImpl implements FollowListService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	FollowDao followDao;

	@Autowired
	NoticeDao noticeDao;
	
	@Override
	public List<UserDto> getFollowerListByNickname(String nickname) {
		String uid = userDao.getUserByUserNickname(nickname).get().getUid();
		return getFollowerListByUid(uid);
	}

	@Override
	public List<UserDto> getFollowingListByNickname(String nickname) {
		String uid = userDao.getUserByUserNickname(nickname).get().getUid();
		return getFollowingListByUid(uid);
	}

	@Override
	public List<UserDto> getFollowerListByUid(String uid) {
		
		List<User> users = userDao.getFollowers(uid);
		List<UserDto> followerUserDtoList = new ArrayList<UserDto>();
		
		for (User user : users) {
			UserDto userDto = new UserDto(user);
			
			if(followDao.existsByFollowFromUidAndFollowToUid(uid, user.getUid())) {
				userDto.setFollowState(1);
			} 
			// Follow 신청 하고 있는 경우 followState 를 2로 설정
			else if(noticeDao.existsByNoticeTypeAndNoticeUidAndNoticeFromUid(1, user.getUid(), uid)) {
				userDto.setFollowState(2);
			}
			else {
				userDto.setFollowState(0);
			}
			followerUserDtoList.add(userDto);
		}
		
		return followerUserDtoList;
	}

	@Override
	public List<UserDto> getFollowingListByUid(String uid) {
		
		List<User> users = userDao.getFollowings(uid);
		List<UserDto> followingUserDtoList = new ArrayList<UserDto>();
		
		for (User user : users) {
			UserDto userDto = new UserDto(user);
			
			if(followDao.existsByFollowFromUidAndFollowToUid(uid, user.getUid())) {
				userDto.setFollowState(1);
			} 
			// Follow 신청 하고 있는 경우 followState 를 2로 설정
			else if(noticeDao.existsByNoticeTypeAndNoticeUidAndNoticeFromUid(1, user.getUid(), uid)) {
				userDto.setFollowState(2);
			}
			else {
				userDto.setFollowState(0);
			}
			followingUserDtoList.add(userDto);
		}
		
		return followingUserDtoList;
		
	}

	@Override
	public List<String> getFollowingUidListByUid(String uid) {
		return followDao.getFollowings(uid);
	}
	
	@Override
	public int getFollowerCount(String userNickname) {
		String uid = userDao.getUserByUserNickname(userNickname).get().getUid();
		return getFollowerListByUid(uid).size();
	}

	@Override
	public int getFollowingCount(String userNickname) {
		String uid = userDao.getUserByUserNickname(userNickname).get().getUid();
		return getFollowingListByUid(uid).size();
	}

}
