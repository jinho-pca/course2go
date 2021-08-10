package com.course2go.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.course2go.model.notice.Notice;

public interface NoticeDao extends JpaRepository<Notice, Integer> {
	
	List<Notice> findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(boolean isnew, int noticeType, String noticeUid);
	
	List<Notice> findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(boolean isnew, Iterable<Integer> noticeType, String noticeUid);
	
	Notice findNoticeById(int id);
	
	@Transactional
	@Modifying
	@Query( value = "UPDATE Notice n" +
					" SET n.notice_isnew = 0" + 
					" WHERE n.notice_uid = :noticeUid AND n.notice_type in :noticeType"
			, nativeQuery = true)
	void updateNew2Old(String noticeUid, Iterable<Integer> noticeType);

}