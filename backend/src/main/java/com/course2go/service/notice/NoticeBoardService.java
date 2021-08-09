package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;

public interface NoticeBoardService {

	void update(String noticeUid);

	public List<NoticeDto> noticeNewBoard(String noticeUid);
	
	public List<NoticeDto> noticeOldBoard(String noticeUid);
	
	public List<Notice> noticeNewComment(String noticeUid);

	public List<Notice> noticeOldComment(String noticeUid);

	public List<Notice> noticeNewScrap(String noticeUid);

	public List<Notice> noticeOldScrap(String noticeUid);

}
