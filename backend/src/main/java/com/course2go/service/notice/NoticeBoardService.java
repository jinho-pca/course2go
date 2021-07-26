package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;

public interface NoticeBoardService {

	public List<Notice> noticeNewComment(String noticeUid);

	public List<Notice> noticeOldComment(String noticeUid);

	public List<Notice> noticeNewScrap(String noticeUid);

	public List<Notice> noticeOldScrap(String noticeUid);

}
