package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;

public interface NoticeScrapService {

	public List<Notice> noticeNewScrap(int noticeUid);

	public List<Notice> noticeOldScrap(int noticeUid);
}
