package com.axelor.gst.service;

import com.axelor.gst.db.InvoiceLine;

public interface productService {

	public InvoiceLine setProduct(InvoiceLine invoiceLine);
	public InvoiceLine selectProduct(int id);
}
