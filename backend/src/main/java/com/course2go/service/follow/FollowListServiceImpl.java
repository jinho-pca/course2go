package com.course2go.service.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.FollowDao;
import com.course2go.dao.UserDao;
import com.course2go.model.follow.Follow;
import com.course2go.model.follow.MappedFollow;
import com.course2go.model.user.User;

@Service
public class FollowListServiceImpl implements FollowListService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	FollowDao followDao;

	@Override
	public List<String> getFollowerListByNickname(String nickname) {
		String uid = userDao.getUserByUserNickname(nickname).get().getUid();
		return getFollowerListByUid(uid);
	}

	@Override
	public List<String> getFollowingListByNickname(String nickname) {
		String uid = userDao.getUserByUserNickname(nickname).get().getUid();
		return getFollowingListByUid(uid);
	}

	@Override
	public List<String> getFollowerListByUid(String uid) {
		return followDao.getFollowers(uid);
	}

	@Override
	public List<String> getFollowingListByUid(String uid) {
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
