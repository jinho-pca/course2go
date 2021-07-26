package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.notice.Notice;

public interface NoticeDao extends JpaRepository<Notice, Integer> {
	
	List<Notice> findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(boolean isnew, int noticeType, String noticeUid);
	
	List<Notice> findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(boolean isnew, Iterable<Integer> noticeType, String noticeUid);

}