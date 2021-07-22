package com.course2go.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;

public class NoticeFollowresultServiceImpl implements NoticeFollowresultService {

	@Autowired
	NoticeDao noticeDao;
	
	private final boolean isnew=true;
	private final int followresult=2;
	@Override
	public List<Notice> noticeNewFollowresult(int noticeUid){		
		return noticeDao.findAllByIsnewAndNoticeTypeAndNoticeUid(isnew, followresult, noticeUid);
	}
	@Override
	public List<Notice> noticeOldFollowresult(int noticeUid){		
		return noticeDao.findAllByIsnewAndNoticeTypeAndNoticeUid(!isnew, followresult, noticeUid);
	}
}
