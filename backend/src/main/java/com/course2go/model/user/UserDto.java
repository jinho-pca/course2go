package com.course2go.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String userName;

    private String userNickname;

    private String userImage;
    
    // 0 = 팔로우 안함, 1 = 팔로우 중 , 2 = 팔로우 신청 해놓음
    private int followState;
    
    public UserDto(String userName, String userNickname, String userImage) {
    	this.userName = userName;
    	this.userNickname = userNickname;
    	this.userImage = userImage;
    	this.followState = 0;
    }
    
    public UserDto(User user) {
    	this.userName = user.getUserName();
    	this.userNickname = user.getUserNickname();
    	this.userImage = user.getUserImage();
    	this.followState = 0;
    }
    
}
