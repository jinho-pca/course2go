package com.course2go.model.notice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notice{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nid;

    private String noticeUid;
    private int noticeType;
    private String noticeFromUid;
    private int noticeNnid;
    private boolean noticeIsnew;

}