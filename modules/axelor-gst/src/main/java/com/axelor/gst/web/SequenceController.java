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

	public void setNextNumber(ActionRequest request, ActionResponse response) {
		
		Sequence sequence = request.getContext().asType(Sequence.class);
		sequence =Beans.get(SequenceService.class).setNextNumber(sequence);
 		response.setValue("nextNumber", sequence.getNextNumber());
		}
	
	public void getSequence(ActionRequest request, ActionResponse response) throws Exception {
		Context context = request.getContext();

		if (context.get("_model").equals(Party.class.getCanonicalName())) {
			Party party = context.asType(Party.class);

			if (party.getReference() == null) {
				try {
				party.setReference(Beans.get(SequenceService.class).getSequence(Party.class.getSimpleName()));
				} catch (Exception e) {
				response.setError("Sequence does not exists!");
				}
				response.setValue("reference", party.getReference());
			}
		}

		if (context.get("_model").equals(Invoice.class.getCanonicalName())) {
			Invoice invoice = context.asType(Invoice.class);

			if (invoice.getReference() == null) {
				try {
					invoice.setReference(Beans.get(SequenceService.class).getSequence(Invoice.class.getSimpleName()));
				} catch (Exception e) {
					response.setError("Sequence does not exists!");
				}
				response.setValue("reference", invoice.getReference());
			}

		}


}


	
}



