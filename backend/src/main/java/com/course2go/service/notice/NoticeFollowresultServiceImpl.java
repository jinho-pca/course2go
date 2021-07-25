package com.course2go.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;

@Service
public class NoticeFollowresultServiceImpl implements NoticeFollowresultService {

	@Autowired
	NoticeDao noticeDao;
	
	private final boolean isnew=true;
	private final int followresult=2;
	@Override
	public List<Notice> noticeNewFollowresult(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, followresult, noticeUid);
	}
	@Override
	public List<Notice> noticeOldFollowresult(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, followresult, noticeUid);
	}
}
