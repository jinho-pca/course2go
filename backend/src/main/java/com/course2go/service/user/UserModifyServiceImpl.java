package com.course2go.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserModifyServiceImpl implements UserModifyService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int userModify(User user) {
		
		if(userDao.findUserByUserEmail(user.getUserEmail()).isPresent()) { // 회원정보 수정을 요청한 유저가 존재하는 유저인 경우
			
			Optional<User> result = userDao.findUserByUserEmail(user.getUserEmail());

			// 닉네임 중복인 경우
			if(userDao.existsByUserNickname(user.getUserNickname())) {
				return 0;
			} 
			// 중복이 없고 수정이 가능한 경우
			else {
				result.get().setUserNickname(user.getUserNickname());
				result.get().setUserPassword(user.getUserPassword());
				userDao.save(result.get());
				return 1;
			}
		} else { // 회원정보 수정을 요청한 유저가 존재하지 않는 유저인 경우
			return -1;
		}
		
		
	}

}
