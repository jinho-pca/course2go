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
	public int userProfileModify(String tokenEmail, String requestComment, String imageUrl) {
		
		if(userDao.findUserByUserEmail(tokenEmail).isPresent()) {
			// 프로필 수정 요청한 유저가 존재 하는 경우

			Optional<User> result = userDao.findUserByUserEmail(tokenEmail);
			result.get().setUserImage(imageUrl);
			result.get().setUserComment(requestComment);
			userDao.save(result.get());
			
			return 1;
		}else return 0; // 프로필 수정 요청한 유저가 존재하지 않는 경우
		
	}

}
