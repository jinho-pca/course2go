package com.course2go.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserProfileModifyServiceImpl implements UserProfileModifyService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int userProfileModify(User user) {
		
		if(userDao.findUserByUserNickname(user.getUserNickname()).isPresent()) {
			// 프로필 수정 요청한 유저가 존재 한다면
			Optional<User> result = userDao.findUserByUserNickname(user.getUserNickname());
			result.get().setUserImage(user.getUserImage());
			result.get().setUserComment(user.getUserComment());
			userDao.save(result.get());
			
			return 1;
		}else return 0;
		
	}

}
