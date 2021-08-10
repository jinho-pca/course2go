package com.course2go.service.notice;

import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;

public interface NoticeService {
	public void update(String noticeUid, Iterable<Integer> noticeTypes);
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, int noticeNnid, boolean noticeIsnew);
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, boolean noticeIsnew);
	public void deleteNotice(NoticeDto notice);
	public void deleteNotice(Notice notice);
	public Notice getNotice(int id);
}
