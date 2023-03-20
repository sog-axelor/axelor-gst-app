package com.axelor.gst.web;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Sequence;
import com.axelor.gst.service.SequenceServiceImpl;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.google.inject.Inject;

public class SequenceController {
	
	@Inject SequenceServiceImpl ssi;
	
	public void setNextNumber(ActionRequest request, ActionResponse response) {			//FOR NEXT NUMBER
		Context context =  request.getContext();
		Sequence  seq = context.asType(Sequence.class);
		 seq = ssi.setNextNumber(seq); 
		 response.setValue("nextNumber", seq.getNextNumber());
	}
	
	public void getSequence(ActionRequest request, ActionResponse response)  throws Exception {			
		Context context =  request.getContext();
		
		System.out.println(context.get("_model"));
		
		
		/*
		 * if(context.get("_model").equals(Party.class.getCanonicalName())) { //FOR
		 * PARTY SEQUENCE Party party = context.asType(Party.class);
		 * 
		 * 
		 * 
		 * 
		 * if(party.getReference() == null) { try {
		 * party.setReference(ssi.getSequence(Party.class.getSimpleName()));
		 * }catch(Exception ex){ response.setError("Sequence Does not exist"); }
		 * 
		 * response.setValue("reference",party.getReference());
		 * System.err.println("Reference Number is : " + party.getReference()); } }
		 * 
		 * if(context.get("_model").equals(Invoice.class.getCanonicalName())) { //FOR
		 * INVOICE SEQUENCE Invoice Invoice = context.asType(Invoice.class);
		 * 
		 * if(Invoice.getReference() == null) { try {
		 * Invoice.setReference(ssi.getSequence(Invoice.class.getSimpleName()));
		 * }catch(Exception ex){ response.setError("Sequence Does not exist"); }
		 * response.setValue("reference",Invoice.getReference());
		 * System.err.println("reference Number is : " + Invoice.getReference()); } }
		 */
		
	}
}
