package com.course2go.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeDao noticeDao;
	
	@Override
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, int noticeNnid, boolean noticeIsnew) {
		noticeDao.save(Notice.builder(noticeUid, noticeType, noticeFromUid, noticeNnid, noticeIsnew).build());
	}
	
	@Override
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, boolean noticeIsnew) {
		noticeDao.save(Notice.builder(noticeUid, noticeType, noticeFromUid, null, noticeIsnew).build());
	}
}
