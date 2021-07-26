package com.course2go.service.user;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserEmailFindServiceImpl implements UserEmailFindService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public String userEmailFind(String userNickname, LocalDate userBirthday) {
		
		if(userDao.findUserByUserNicknameAndUserBirthday(userNickname, userBirthday).isPresent()) {
			// 닉네임과 생일이 일치하는 유저가 존재하는 경우
			Optional<User> result = userDao.findUserByUserNicknameAndUserBirthday(userNickname, userBirthday);
			return result.get().getUserPassword();
		}else return "fail";
	}
}
