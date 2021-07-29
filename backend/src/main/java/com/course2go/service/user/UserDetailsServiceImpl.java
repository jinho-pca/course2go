package com.course2go.service.user;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.exception.UserNotFoundException;
import com.course2go.model.user.MyUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	private final UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.getUserByUserEmail(username)
		.map(u -> new MyUserDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserNickname())))).orElseThrow(() -> new UserNotFoundException(username));
	}

}
