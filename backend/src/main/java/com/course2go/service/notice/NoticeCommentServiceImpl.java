package com.course2go.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;

@Service
public class NoticeCommentServiceImpl implements NoticeCommentService {

	@Autowired
	NoticeDao noticeDao;
	
	private final boolean isnew=true;
	private final int comment=4;
	@Override
	public List<Notice> noticeNewComment(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, comment, noticeUid);
	}
	@Override
	public List<Notice> noticeOldComment(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, comment, noticeUid);
	}
}
