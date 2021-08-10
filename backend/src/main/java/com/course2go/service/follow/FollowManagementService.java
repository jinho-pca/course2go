package com.course2go.service.follow;

import com.course2go.model.notice.NoticeDto;

public interface FollowManagementService {
	void deny(NoticeDto noticedto);
	void agree(NoticeDto noticedto);
	void unfollow(String followFromNickname, String followToNickname);
	void follow(String followFromNickname, String followToNickname);
	void makeFollow(String followFromUid, String followToUid);
}
