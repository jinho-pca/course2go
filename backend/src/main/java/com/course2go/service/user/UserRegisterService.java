package com.course2go.service.user;

import com.course2go.model.user.User;

public interface UserRegisterService {
	
	// 회원가입
	public int userRegister(User user);
	
	// 이메일 중복검사
	public boolean userEmailCheck(String userEmail);
	
	// 닉네임 중복검사
	public boolean userNicknameCheck(String userNickname);
}