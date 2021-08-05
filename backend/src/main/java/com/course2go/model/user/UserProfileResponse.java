package com.course2go.model.user;

public class UserProfileResponse {

    String userName;

    String userNickname;

    String userImage;

    String userComment;

    int userFollowing;

    int userFollower;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public int getUserFollowing() { return userFollowing; }

    public void setUserFollowing(int userFollowing) { this.userFollowing = userFollowing; }

    public int getUserFollower() { return userFollower; }

    public void setUserFollower(int userFollower) { this.userFollower = userFollower; }
}
