package com.course2go.service.user;

import com.course2go.model.user.User;

public interface UserModifyService {

	// 회원정보 수정
	public int userModify(String tokenEmail, String userNickname, String userPassword);

	// 회원정보 수정 후 토큰 업데이트
	public String updateToken(String tokenEmail);
}
