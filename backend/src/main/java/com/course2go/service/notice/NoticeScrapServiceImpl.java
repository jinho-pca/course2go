package com.course2go.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;

@Service
public class NoticeScrapServiceImpl implements NoticeScrapService {

	@Autowired
	NoticeDao noticeDao;
	
	private final boolean isnew=true;
	private final int scrap=3;
	@Override
	public List<Notice> noticeNewScrap(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, scrap, noticeUid);
	}
	@Override
	public List<Notice> noticeOldScrap(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, scrap, noticeUid);
	}
}
