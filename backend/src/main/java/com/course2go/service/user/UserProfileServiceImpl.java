package com.course2go.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Object userProfileShow(String userNickname) {
		
		if(userDao.findUserByUserNickname(userNickname).isPresent()) { // 프로필 보기 요청한 유저가 존재하는 경우
			Optional<User> result = userDao.findUserByUserNickname(userNickname);
			result.get().setUid(null);
			return result;
		} else return "fail";
		
	}

}
