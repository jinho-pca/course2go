package com.course2go.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeDao noticeDao;

	@Override
	public void update(String noticeUid, Iterable<Integer> noticeTypes) {
		noticeDao.updateNew2Old(noticeUid, noticeTypes);
	}
	
	@Override
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, int noticeNnid, boolean noticeIsnew) {
		noticeDao.save(Notice.builder(noticeUid, noticeType, noticeFromUid, noticeNnid, noticeIsnew).build());
	}
	
	@Override
	public void writeNotice(String noticeUid, int noticeType, String noticeFromUid, boolean noticeIsnew) {
		noticeDao.save(Notice.builder(noticeUid, noticeType, noticeFromUid, null, noticeIsnew).build());
	}

	@Override
	public void deleteNotice(NoticeDto noticeDto) {
		noticeDao.delete(Notice.builder(noticeDto.getNoticeUid(), noticeDto.getNoticeType(), noticeDto.getNoticeFromUid(), noticeDto.getNoticeNnid(), noticeDto.isNoticeIsnew()).build());
	}
	
	@Override
	public void deleteNotice(Notice notice) {
		noticeDao.delete(notice);
	}

	@Override
	public Notice getNotice(int id) {
		return noticeDao.findNoticeById(id);
	}
	
	
}
