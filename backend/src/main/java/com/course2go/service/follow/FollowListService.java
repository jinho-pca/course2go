package com.course2go.service.follow;

import java.util.List;

public interface FollowListService {

	public List<String> getFollowerListByUid(String uid);
	public List<String> getFollowingListByUid(String uid);
	public List<String> getFollowerList(String email);
	public List<String> getFollowingList(String email);
	
}
