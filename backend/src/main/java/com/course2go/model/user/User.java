package com.course2go.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String uid;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@JsonIgnore
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_nickname")
	private String userNickname;
	
	@Column(name = "user_salt")
	private String userSalt;
	
	@Column(name = "user_birthday")
	private String userBirthday;
	
	@Column(name = "user_gender")
	private int userGender;
	
	@Column(name = "user_image")
	private String userImage;
	
	@Column(name = "user_comment")
	private String userComment;
	
}