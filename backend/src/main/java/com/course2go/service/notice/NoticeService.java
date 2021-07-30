package com.course2go.service.notice;

import com.course2go.model.notice.Notice;

public interface NoticeService {
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, int noticeNnid, boolean noticeIsnew);
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, boolean noticeIsnew);
	public void deleteNotice(Notice notice);
}
