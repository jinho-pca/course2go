package com.course2go.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserDeleteServiceImpl implements UserDeleteService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int userDelete(String userNickname) {
		
		if(userDao.findUserByUserNickname(userNickname).isPresent()) { // 회원탈퇴 요청한 유저가 존재하는 경우
			Optional<User> result = userDao.findUserByUserNickname(userNickname);
			
			userDao.delete(result.get());
			return 1;
			
		} else return 0; // 회원탈퇴 요청한 유저가 존재하지 않는 경우
	}

}
