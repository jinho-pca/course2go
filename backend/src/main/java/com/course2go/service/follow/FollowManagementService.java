package com.course2go.service.follow;

import com.course2go.model.notice.Notice;

public interface FollowManagementService {
	void deny(Notice notice);
	void agree(Notice notice);
	void unfollow(String followFromNickname, String followToNickname);
}
