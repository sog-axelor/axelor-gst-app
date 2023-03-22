package com.axelor.gst.service;

import com.axelor.gst.db.InvoiceLine;

public class ProductServiceImpl implements productService{

	@Override
	public InvoiceLine setProduct(InvoiceLine invoiceLine) {
		String name = invoiceLine.getItem();
		invoiceLine.setItem(name);
		return invoiceLine;
	}

}
