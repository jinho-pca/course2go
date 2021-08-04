package com.course2go.service.visit;

import com.course2go.model.visit.VisitDto;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitResponse;
import com.course2go.model.visit.VisitWriteDto;

public interface VisitService {
	public void writeVisit(String uid, String title, VisitWriteDto dto, Integer rid);
	public void writeVisit(String uid, String title, VisitWriteDto dto);
	public Integer writeVisit(String uid, String title, Integer visitPid, String visitContent, Integer visitTime, Integer visitCost, String visitImage1, String visitImage2, String visitImage3);
	public VisitReadResponse readVisitBoard(Integer bid);
	public VisitResponse readVisit(Integer vid);
	public VisitDto getVisit(Integer vid);
}
