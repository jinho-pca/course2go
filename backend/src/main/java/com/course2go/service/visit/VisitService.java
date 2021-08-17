package com.course2go.service.visit;

import java.util.List;

import com.course2go.model.visit.VisitDto;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitResponse;
import com.course2go.model.visit.VisitWriteDto;

public interface VisitService {
	public void writeVisit(String uid, String title, VisitWriteDto dto, Integer rid);
	public void writeVisit(String uid, String title, VisitWriteDto dto);
	public Integer writeVisit(String uid, String title, Integer visitPid, String visitContent, Integer visitTime, Integer visitCost, String visitImage1, String visitImage2, String visitImage3);
	public VisitReadResponse readVisitBoard(Integer bid, String uid);
	public VisitResponse readVisit(Integer vid);
	public VisitDto getVisit(Integer vid);
	public List<VisitReadResponse> getMyVisitList(String uid1, String uid);
	public List<VisitReadResponse> getVisitList(String userNickname, String uid);
	public List<VisitReadResponse> getVisitListByPid(Integer pid, String uid);
}
