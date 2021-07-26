package com.course2go.service.follow;

import java.util.List;
import com.course2go.model.user.User;

public interface FollowListService {
	
	public List<String> getFollowerList(String uid);
	public List<String> getFollowingList(String uid);
	
}
