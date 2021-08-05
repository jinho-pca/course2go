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
	public List<String> getFollowerList(String email) {
		String uid = userDao.getUserByUserEmail(email).get().getUid();
		return getFollowerListByUid(uid);
	}

	@Override
	public List<String> getFollowingList(String email) {
		String uid = userDao.getUserByUserEmail(email).get().getUid();
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
	
}
