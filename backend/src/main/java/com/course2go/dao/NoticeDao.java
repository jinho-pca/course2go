package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.notice.Notice;

public interface NoticeDao extends JpaRepository<Notice, String> {
	
	List<Notice> findAllByIsnewAndNoticeTypeAndNoticeUid(boolean isnew, int noticeType, int noticeUid);

}