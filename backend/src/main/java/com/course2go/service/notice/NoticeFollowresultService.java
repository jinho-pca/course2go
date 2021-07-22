package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;

public interface NoticeFollowresultService {

	public List<Notice> noticeNewFollowresult(int noticeUid);

	public List<Notice> noticeOldFollowresult(int noticeUid);
}
