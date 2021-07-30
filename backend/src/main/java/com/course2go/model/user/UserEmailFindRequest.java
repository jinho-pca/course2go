package com.course2go.model.user;

import java.time.LocalDate;

public class UserEmailFindRequest {
	
	String userNickname;
	
	LocalDate userBirthday;

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public LocalDate getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(LocalDate userBirthday) {
		this.userBirthday = userBirthday;
	}
	
	
}
