package com.course2go.service.notice;

public interface NoticeService {
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, int noticeNnid, boolean noticeIsnew);
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, boolean noticeIsnew);
}
