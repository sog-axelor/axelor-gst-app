package com.axelor.gst.web;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.axelor.db.JPA;
import com.axelor.db.JpaSupport;
import com.axelor.gst.db.Contact;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.axelor.gst.service.GstService;
import com.axelor.gst.service.GstServiceImpl;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

public class GstController extends JpaSupport {

	@Inject
	private GstServiceImpl gsi;

	public void say(ActionRequest request, ActionRequest response) {

		System.err.println(gsi.hello());
	}

	public void sequence(ActionRequest request, ActionResponse response) {
		
		Context context = request.getContext();
		Sequence sequence = context.asType(Sequence.class);
		if (sequence.getId() != null) {
			sequence = Beans.get(SequenceRepository.class).find(sequence.getId());
			gsi.SequenceGenerator(sequence.getPadding(), sequence.getPrefix(), sequence.getSuffix());
			response.setAlert("Next Number is : " + sequence.getNextNumber());

			System.err.println(sequence.getNextNumber());
		}
	}

	public void grossAmount(ActionRequest request, ActionResponse response) {
		Invoice invoice = request.getContext().asType(Invoice.class);

		invoice = gsi.calculateInvoice(invoice);
		response.setValue("grossAmount", invoice.getGrossAmount());
		response.setValue("netAmount", invoice.getNetAmount());
		response.setValue("netCgst", invoice.getNetCgst());
		response.setValue("netSgst", invoice.getNetSgst());
		response.setValue("netIgst", invoice.getNetIgst());
		
		System.err.println("netIgst Amount is : " +invoice.getNetIgst());
		System.err.println("netsgst Amount is : " +invoice.getNetSgst());
		System.err.println("netcGst Amount is : " + invoice.getNetCgst());
		System.err.println("Gross Amount is : " + invoice.getGrossAmount());
		System.err.println("netAmount is : " + invoice.getNetAmount());
	}

	public void calculateGros(ActionRequest request, ActionResponse response) {
		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		invoiceline = gsi.calculateGrossAmount(invoiceline);
		response.setValue("grossAmount", invoiceline.getGrossAmount());
	}
	
	public void igstValidation(ActionRequest request, ActionResponse response) {
		Context context = request.getContext();
		InvoiceLine invoiceline = context.asType(InvoiceLine.class);
		Invoice invoice =request.getContext().getParentContext().asType(Invoice.class);
		
		
		if(invoice.getCompany() != null && invoice.getCompany().getAddress() != null
				&& invoice.getCompany().getAddress().getState() != null) {
			
			if (invoice.getCompany().getAddress().getState() != invoice.getInvoiceAddress().getState()) {
				invoiceline = gsi.calculateIgst(invoiceline);
				response.setValue("igst", invoiceline.getIgst());
			}else {
				invoiceline = gsi.calculateSgst(invoiceline);
				response.setValue("sgst", invoiceline.getSgst());
		
				invoiceline = gsi.calculateCgst(invoiceline);
				response.setValue("cgst", invoiceline.getCgst());
				}
			
			
			}else if (invoice.getCompany() == null && invoice.getCompany().getAddress() == null
				&& invoice.getCompany().getAddress().getState() == null) {
				response.setAlert("Invoice Address need to be fill");
			}
		invoiceline = gsi.calculateGrossAmount(invoiceline);
		response.setValue("grossAmount", invoiceline.getGrossAmount());
		
		
		invoiceline = gsi.calculateNetAmount(invoiceline);
		response.setValue("netAmount", invoiceline.getNetAmount());
		}
	

		
			public void getSequence(ActionRequest request, ActionResponse response) throws Exception {
				Context context = request.getContext();
		
			}
}










