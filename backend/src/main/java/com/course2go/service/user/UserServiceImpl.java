package com.course2go.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public Optional<User> getUserByUserNickname(String userNickname) {
		return userDao.getUserByUserNickname(userNickname);
	}

}
