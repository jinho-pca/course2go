package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;

public interface NoticeCommentService {

	public List<Notice> noticeNewComment(String noticeUid);

	public List<Notice> noticeOldComment(String noticeUid);
}
