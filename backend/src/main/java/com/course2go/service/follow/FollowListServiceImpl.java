package com.course2go.service.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.FollowDao;
import com.course2go.dao.UserDao;
import com.course2go.model.follow.Follow;
import com.course2go.model.user.User;

@Service
public class FollowListServiceImpl implements FollowListService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	FollowDao followDao;
	
	
	@Override
	public List<User> getFollowerList(String email) {
		String uid = userDao.getUserByUserEmail(email).getUid();
		System.out.println(uid);
		List<User> list = userDao.getFollowers(uid);
		return list;
	}

	@Override
	public List<User> getFollowingList(String email) {
		String uid = userDao.getUserByUserEmail(email).getUid();
		List<User> list = userDao.getFollowings(uid);
		return list;
	}
	
}
