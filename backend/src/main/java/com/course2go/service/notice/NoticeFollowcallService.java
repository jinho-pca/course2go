package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;

public interface NoticeFollowcallService {
	public List<Notice> noticeNewFollowcall(String noticeUid);

	public List<Notice> noticeOldFollowcall(String noticeUid);
}
