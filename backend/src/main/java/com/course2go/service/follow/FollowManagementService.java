package com.course2go.service.follow;

import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;

public interface FollowManagementService {
	void deny(Notice notice);
	void agree(Notice notice);
	void unfollow(String followFromNickname, String followToNickname);
	void follow(String followFromNickname, String followToNickname);
}
