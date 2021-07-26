package com.course2go.service.follow;

import com.course2go.model.notice.Notice;

public interface FollowManagementService {
	boolean deny(Notice notice);
	boolean agree(Notice notice);
	boolean unfollow(String followFromNickname, String followToNickname);
}
