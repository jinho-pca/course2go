package com.course2go.service.user;

import java.time.LocalDate;

public interface UserPasswordFindService {
	
	// 비밀번호 찾기
	public int userPasswordFind(String userEmail, String userNickname, LocalDate userBirthday);
}
