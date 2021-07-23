package com.course2go.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;

@Service
public class NoticeFollowcallServiceImpl implements NoticeFollowcallService{
	
	@Autowired
	NoticeDao noticeDao;
	
	private final boolean isnew=true;
	private final int followcall=1;
	private final int followresult=2;
	private final int scrap=3;
	private final int comment=4;
	
	@Override
	public List<Notice> noticeNewFollowcall(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, followcall, noticeUid);
	}
	@Override
	public List<Notice> noticeOldFollowcall(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, followcall, noticeUid);
	}
}