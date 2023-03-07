package com.axelor.gst.service;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;

public interface GstService {
		
	String hello();
	String getNext();
	void SequenceGenerator(int paddingLength, String prefix, String suffix);
	
	public InvoiceLine calculateNetAmount(InvoiceLine invoiceline);
	
	public InvoiceLine calculateIgst(InvoiceLine invoiceline);
	
	public InvoiceLine calculateSgst(InvoiceLine invoiceline);
	
	public InvoiceLine calculateCgst(InvoiceLine invoiceline);
	
	public InvoiceLine calculateGrossAmount(InvoiceLine invoiceline);
}
