package com.axelor.gst.service;

import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Product;
import com.axelor.gst.db.ProductCategory;
import com.axelor.gst.db.Sequence;

public interface GstService {
		
	String hello();
	String getNext();
		
	public InvoiceLine calculateNetAmount(InvoiceLine invoiceline);
	
	public InvoiceLine calculateIgst(InvoiceLine invoiceline);
	
	public InvoiceLine calculateSgst(InvoiceLine invoiceline);
	
	public InvoiceLine calculateCgst(InvoiceLine invoiceline);
	
	public InvoiceLine calculateGrossAmount(InvoiceLine invoiceline);
}
