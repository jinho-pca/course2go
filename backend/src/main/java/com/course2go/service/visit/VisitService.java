package com.course2go.service.visit;

import com.course2go.model.visit.VisitDto;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitResponse;
import com.course2go.model.visit.VisitWriteDto;
import com.course2go.model.visit.VisitWriteRequest;

public interface VisitService {
	public void writeVisit(String uid, VisitWriteDto dto);
	public void writeVisit(String uid, Integer visitPid, String visitContent, Integer visitTime, Integer visitCost, String visitImage1, String visitImage2, String visitImage3);
	public VisitReadResponse readVisitBoard(Integer bid);
	public VisitResponse readVisit(Integer vid);
	public VisitDto getVisit(Integer vid);
}
