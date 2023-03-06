package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;

public class GstServiceImpl implements GstService {

	@Override
	public String hello() {
		return "Hello World";
	}
	
    private long nextNumber = 1;

	@Override
	public long sequenceGenrator() {
		return nextNumber++;
	}
	
	


}
