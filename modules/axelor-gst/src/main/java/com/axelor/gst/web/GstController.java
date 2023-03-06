package com.axelor.gst.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.axelor.db.JpaSupport;
import com.axelor.gst.db.Sequence;
import com.axelor.gst.service.GstService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.Context;

public class GstController extends JpaSupport {

	@Inject GstService gs;
	
	public void say(ActionRequest request, ActionRequest response) {
		
	//Context context = request.getContext();
			System.err.println(gs.hello());	
	}
	
	/*
	 * Prefix + number of digits as per padding starting with 0 + Suffix "Example :
	 * ABC0000, ABC0001,ABC0003 (Here Prefix: = ABC, Suffix = null, Padding = 4,
	 * Increment on each call to get this number) "
	 */	
	public void sequenceGenrater(ActionRequest request, ActionRequest response) {
		Context context = request.getContext();
		Sequence seq = context.asType(Sequence.class);
		
		
		
	}
}
