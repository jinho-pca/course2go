package com.course2go.service.notice;

import java.util.List;

import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;

public interface NoticeFollowService {
	
	public List<NoticeDto> noticeNewFollow(String noticeUid);
	
	public List<NoticeDto> noticeOldFollow(String noticeUid);
	
	public List<Notice> noticeNewFollowcall(String noticeUid);

	public List<Notice> noticeOldFollowcall(String noticeUid);

	public List<Notice> noticeNewFollowresult(String noticeUid);

	public List<Notice> noticeOldFollowresult(String noticeUid);

}
