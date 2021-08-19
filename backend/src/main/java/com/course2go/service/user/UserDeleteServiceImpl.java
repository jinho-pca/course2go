package com.course2go.service.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.course2go.dao.FollowDao;
import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserDeleteServiceImpl implements UserDeleteService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public int userDelete(String tokenEmail) {

		// 회원탈퇴 요청한 유저가 존재하는 경우
		if(userDao.findUserByUserEmail(tokenEmail).isPresent()) {
			Optional<User> result = userDao.findUserByUserEmail(tokenEmail);
			User newUser = result.get();
			//			String uid = result.get().getUid();
//			followDao.deleteAllByFollowFromUid(uid);
//			followDao.deleteAllByFollowToUid(uid);
//			userDao.delete(result.get());
			
			newUser.setUserNickname(null);
			newUser.setUserEmail(null);
			userDao.save(newUser);
			return 1;

		} else return 0; // 회원탈퇴 요청한 유저가 존재하지 않는 경우
	}

}
