package com.course2go.service.follow;

import java.util.List;

public interface FollowListService {

	public List<String> getFollowerListByUid(String uid);
	public List<String> getFollowingListByUid(String uid);
	public List<String> getFollowerListByNickname(String nickname);
	public List<String> getFollowingListByNickname(String nickname);
	public int getFollowerCount(String userNickname);
	public int getFollowingCount(String userNickname);
	
}
