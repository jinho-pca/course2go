package com.course2go.model.user;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.course2go.model.follow.Follow.FollowBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(builderMethodName = "UserBuilder")
public class User {
	@Id
	private String uid;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_email")
	private String userEmail;
	
//	@JsonIgnore
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_nickname")
	private String userNickname;
	
	@Column(name = "user_salt")
	private String userSalt;
	
	
	@Column(name = "user_birthday")
	private LocalDate userBirthday;
	
	@Column(name = "user_gender")
	private boolean userGender;
	
	@Column(name = "user_image")
	private String userImage;
	
	@Column(name = "user_comment")
	private String userComment;
	
	public static UserBuilder builder(String uid,String userName, String userEmail, String userPassword, String userNickname, String userSalt,
			LocalDate userBirthday, boolean userGender) {
		return UserBuilder()
				.uid(uid)
				.userName(userName)
				.userEmail(userEmail)
				.userPassword(userPassword)
				.userNickname(userNickname)
				.userSalt(userSalt)
				.userBirthday(userBirthday)
				.userGender(userGender);
	}
	
	
}