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
		System.out.println(uid);
		List<String> list = followDao.getFollowers(uid);
		for (String string : list) {
			System.out.println(string);
		}
		return list;
	}

	@Override
	public List<String> getFollowingList(String email) {
		String uid = userDao.getUserByUserEmail(email).get().getUid();
		List<String> list = followDao.getFollowings(uid);
		return list;
	}
	
}
