package com.course2go.service.user;

import java.time.LocalDate;

public interface UserEmailFindService {
	
	// 이메일 찾기
	public String userEmailFind(String userNickname, LocalDate userBirthday);
}
