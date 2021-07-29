package com.course2go.service.user;

import com.course2go.model.user.SignupRequest;

public interface UserRegisterService {
	
	// 회원가입
	public int userRegister(SignupRequest signupRequest);
	
	// 이메일 중복검사
	public boolean userEmailCheck(String userEmail);
	
	// 닉네임 중복검사
	public boolean userNicknameCheck(String userNickname);
}