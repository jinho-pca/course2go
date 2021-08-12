package com.course2go.service.follow;

import java.util.List;
import com.course2go.model.user.UserDto;

public interface FollowListService {

	public List<UserDto> getFollowerListByUid(String uid);
	public List<UserDto> getFollowingListByUid(String uid);
	public List<UserDto> getFollowerListByNickname(String nickname);
	public List<UserDto> getFollowingListByNickname(String nickname);
	public List<String> getFollowingUidListByUid(String uid);
	public int getFollowerCount(String userNickname);
	public int getFollowingCount(String userNickname);
	
}
