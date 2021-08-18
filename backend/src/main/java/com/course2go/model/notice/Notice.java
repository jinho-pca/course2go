package com.course2go.model.notice;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "NoticeBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notice{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String noticeUid;
    private Integer noticeType;
    private String noticeFromUid;
    private Integer noticeNnid;
    private Boolean noticeIsnew;

    
    @Column(insertable = false, updatable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime noticeTime;
    
    public static NoticeBuilder builder(String noticeUid, Integer noticeType, String noticeFromUid, Integer noticeNnid, boolean noticeIsnew) {
		return NoticeBuilder()
				.noticeUid(noticeUid)
				.noticeType(noticeType)
				.noticeFromUid(noticeFromUid)
				.noticeNnid(noticeNnid)
				.noticeIsnew(noticeIsnew);
	}
}