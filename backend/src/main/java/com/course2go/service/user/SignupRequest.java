package com.course2go.service.user;

import java.time.LocalDate;

public class SignupRequest {

	String userName;
	
	String userEmail;
	
	String userPassword;
	
	String userNickname;
    
	LocalDate userBirthday;
    
	boolean userGender;

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

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

	public boolean getUserGender() {
		return userGender;
	}

	public void setUserGender(boolean userGender) {
		this.userGender = userGender;
	}
	
}
