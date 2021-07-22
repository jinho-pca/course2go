package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;

public interface NoticeFollowcallService {
	public List<Notice> noticeNewFollowcall(int noticeUid);

	public List<Notice> noticeOldFollowcall(int noticeUid);
}
