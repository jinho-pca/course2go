package com.course2go.model.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCreateRequest {
    private String noticeUid;
    private Integer noticeType;
    private String noticeFromUid;
    private Integer noticeNnid;
    private boolean noticeIsnew;
}
