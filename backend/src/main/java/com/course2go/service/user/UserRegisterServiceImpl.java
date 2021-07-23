package com.course2go.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

public class UserRegisterServiceImpl implements UserRegisterService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int userRegister(User user) {
		User result = new User();
		
		// 이메일 중복인 경우
		if(userEmailCheck(user.getUserEmail())) {
			return 0;
		}
		
		// 닉네임 중복인 경우
		if(userNicknameCheck(user.getUserNickname())) {
			return -1;
		}
		
		// 중복이 없고 가입이 가능한 경우
		result.setUserEmail(user.getUserEmail());
		result.setUserPassword(user.getUserPassword());
		result.setUserNickname(user.getUserNickname());
		result.setUserName(user.getUserName());
		result.setUserGender(user.getUserGender());
		result.setUserBirthday(user.getUserBirthday());
		userDao.save(result);
		return 1;
	}

	@Override
	public boolean userEmailCheck(String userEmail) {
		// 이메일 중복인 경우
		if(userDao.existsByUserEmail(userEmail))
			return true;
		// 이메일 중복이 아닌 경우
		else return false;
	}

	@Override
	public boolean userNicknameCheck(String userNickname) {
		// 닉네임이 중복인 경우
		if(userDao.existsByUserNickname(userNickname))
			return true;
		// 닉네임 중복이 아닌 경우
		else return false;
	}

}
