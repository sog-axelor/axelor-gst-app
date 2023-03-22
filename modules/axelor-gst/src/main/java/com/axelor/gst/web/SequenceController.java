package com.axelor.gst.web;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Sequence;
import com.axelor.gst.service.SequenceService;
import com.axelor.gst.service.SequenceServiceImpl;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.google.inject.Inject;

public class SequenceController {

	@Inject
	SequenceServiceImpl ssi;

	public void setNextNumber(ActionRequest request, ActionResponse response) { // FOR NEXT NUMBER
		Context context = request.getContext();
		Sequence seq = context.asType(Sequence.class);
		seq = ssi.setNextNumber(seq);
		response.setValue("nextNumber", seq.getNextNumber());
	}

	public void getSequence(ActionRequest request, ActionResponse response) throws Exception {
		Context context = request.getContext();

		System.err.println("Model is : " + context.get("_model"));
		System.err.println("Class name is : " + Party.class.getCanonicalName());
		System.err.println("CLass name is : " + Party.class.getSimpleName());

		// FOR PARTY SEQUENCE
		Party party = context.asType(Party.class);
		if (party.getReference() == null) {
			try {
				party.setReference(ssi.getSequence(Party.class.getName()));
			} catch (Exception ex) {
				response.setError("Sequence Does not exist");
			}
			response.setValue("reference", party.getReference());
			System.err.println("Reference Number is : " + party.getReference());
		}
	}
	
	
	public void getInvoiceSeq(ActionRequest request, ActionResponse response) throws Exception  {
		Context context = request.getContext();
		// FOR INVOICE SEQUENCE
				Invoice invoice = context.asType(Invoice.class);
				if (invoice.getReference() == null) {
					try {
						invoice.setReference(ssi.getSequence(Invoice.class.getName()));
					} catch (Exception ex) {
						response.setError("Sequence Does not exist");
					}
					response.setValue("reference", invoice.getReference());
					System.err.println("Reference Number is : " + invoice.getReference());
				}
	}
}



