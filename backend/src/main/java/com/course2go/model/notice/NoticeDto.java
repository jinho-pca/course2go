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
}
