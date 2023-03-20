package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;

public interface SequenceService {
	
	public Sequence setNextNumber(Sequence sequence);
	public String calculateNextSequence(Sequence seq, String padnext);
	public String getSequence(String modelName) throws Exception;
	
}
