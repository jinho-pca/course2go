package com.course2go.service.visit;

import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitResponse;
import com.course2go.model.visit.VisitWriteRequest;

public interface VisitService {
	public void writeVisit(String uid, VisitWriteRequest request);
	public void writeVisit(String uid, Integer visitPid, String visitContent);
	public VisitReadResponse readVisitBoard(Integer bid);
	public VisitResponse readVisit(Integer vid);
}
