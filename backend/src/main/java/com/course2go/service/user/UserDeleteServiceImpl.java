package com.course2go.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserDeleteServiceImpl implements UserDeleteService{

	@Autowired
	private UserDao userDao;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public int userDelete(String tokenEmail, String requestPassword) {

		// 회원탈퇴 요청한 유저가 존재하는 경우
		if(userDao.findUserByUserEmail(tokenEmail).isPresent()) {
			Optional<User> result = userDao.findUserByUserEmail(tokenEmail);

			String salt = result.get().getUserSalt(); // 탈퇴요청한 유저의 salt

			if(!passwordEncoder.matches(requestPassword + salt, result.get().getUserPassword())){
				// 비밀번호가 틀린 경우
				return 0;
			}else{
				// 탈퇴 요청을 한 유저의 비밀번호가 일치하는 경우
				userDao.delete(result.get());
				return 1;
			}

		} else return -1; // 회원탈퇴 요청한 유저가 존재하지 않는 경우
	}

}
