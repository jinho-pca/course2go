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
    
    private boolean isFollowing = false;
    
    public UserDto(String userName, String userNickname, String userImage) {
    	this.userName = userName;
    	this.userNickname = userNickname;
    	this.userImage = userImage;
    	this.isFollowing = false;
    }
    
}
