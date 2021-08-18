package com.course2go.model.notice;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
    private Integer id;
    private String noticeUid;
    private Integer noticeType;
    private String noticeFromUid;
    private String noticeFromUserNickname;
    private Integer noticeNnid;
    private boolean noticeIsnew;
    private LocalDateTime noticeTime;
    private Integer bid;
    private Integer vid = -1;
    
    public NoticeDto(Notice notice, String noticeFromUserNickname, Integer bid, Integer vid) {
    	this.id = notice.getId();
    	this.noticeUid = notice.getNoticeUid();
    	this.noticeType = notice.getNoticeType();
    	this.noticeFromUid = notice.getNoticeFromUid();
    	this.noticeFromUserNickname = noticeFromUserNickname;
    	this.noticeNnid = notice.getNoticeNnid();
    	this.noticeIsnew = notice.getNoticeIsnew();
    	this.noticeTime = notice.getNoticeTime();
    	this.bid = bid;
    	this.vid = vid;
    }
    
    public NoticeDto(Notice notice, String noticeFromUserNickname, Integer bid) {
    	this.id = notice.getId();
    	this.noticeUid = notice.getNoticeUid();
    	this.noticeType = notice.getNoticeType();
    	this.noticeFromUid = notice.getNoticeFromUid();
    	this.noticeFromUserNickname = noticeFromUserNickname;
    	this.noticeNnid = notice.getNoticeNnid();
    	this.noticeIsnew = notice.getNoticeIsnew();
    	this.noticeTime = notice.getNoticeTime();
    	this.bid = bid;
    }
    
}
