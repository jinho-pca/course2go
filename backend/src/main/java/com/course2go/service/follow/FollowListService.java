package com.course2go.service.follow;

import java.util.List;
import com.course2go.model.user.User;

public interface FollowListService {
	
	public List<User> getFollowerList(String uid);
	public List<User> getFollowingList(String uid);
	
}
