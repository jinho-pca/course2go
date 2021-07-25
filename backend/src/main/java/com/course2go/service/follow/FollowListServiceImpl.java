package com.course2go.service.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.FollowDao;
import com.course2go.model.follow.Follow;
import com.course2go.model.user.User;

@Service
public class FollowListServiceImpl implements FollowListService{

//	@Autowired
//	UserDao userDao;
	
	@Autowired
	FollowDao followDao;
	
	
	@Override
	public List<User> getFollowerList(String email) {
		
//		userDao 추가 후 바꿔야함
//		String uid = userDao.getUserByUserEmail(email).getUid();
		String uid = "1";
		
		List<User> list = followDao.getFollowers(uid);
		System.out.println("follower목록");
		for (User follow : list) {
			System.out.println(follow.toString());			
		}

//		List<Follow> list = followDao.get();
//		for (Follow follow : list) {
//			System.out.println(follow.toString());
//		}
		
		
		return null;
	}

	@Override
	public List<User> getFollowingList(String email) {
		
		return null;
	}
	
}
