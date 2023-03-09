package com.axelor.gst.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.axelor.common.ObjectUtils;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.repo.ContactRepository;
import com.axelor.gst.db.repo.InvoiceRepository;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class GstServiceImpl implements GstService {

	@Override
	public String hello() {
		return "Hello World";
	}

	private int nextNumber;
	private int padding;
	private String prefix;
	private String suffix;

	public void SequenceGenerator(int padding, String prefix, String suffix) {
		this.nextNumber = 0;
		this.padding = padding;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	@Override
	public String getNext() {
		String paddedCounter = String.format("%0" + padding + "d", nextNumber);
		String sequence = prefix + paddedCounter + suffix;
		nextNumber++;
		return sequence;
	}

	public Invoice calculateInvoice(Invoice invoice) {
		BigDecimal grossAmount = BigDecimal.ZERO;
		BigDecimal netAmount = BigDecimal.ZERO;
		BigDecimal cgst = BigDecimal.ZERO;
		BigDecimal sgst = BigDecimal.ZERO;
		BigDecimal igst = BigDecimal.ZERO;

		if (ObjectUtils.isEmpty(invoice.getInvoiceItems())) {
			return invoice;
		}

//		Beans.get(ContactRepository.class).all().filter("self.type =?1","Primary").fetch()
		
		cgst = invoice.getInvoiceItems().stream().map(InvoiceLine::getCgst).reduce(BigDecimal.ZERO,BigDecimal::add);
		sgst = invoice.getInvoiceItems().stream().map(InvoiceLine::getSgst).reduce(BigDecimal.ZERO,BigDecimal::add);
		igst = invoice.getInvoiceItems().stream().map(InvoiceLine::getIgst).reduce(BigDecimal.ZERO,BigDecimal::add);
		
		netAmount = invoice.getInvoiceItems().stream().map(InvoiceLine::getNetAmount).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		grossAmount = invoice.getInvoiceItems().stream().map(InvoiceLine::getGrossAmount).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		invoice.setGrossAmount(grossAmount);
		invoice.setNetAmount(netAmount);
		invoice.setNetCgst(cgst);
		invoice.setNetSgst(sgst);
		invoice.setNetSgst(igst);
		return invoice;
	}

	@Override
	public InvoiceLine calculateNetAmount(InvoiceLine invoiceline) {
		BigDecimal netAmount = invoiceline.getPrice().multiply(new BigDecimal(invoiceline.getQty()));
		invoiceline.setNetAmount(netAmount);
		return invoiceline;
	}

	@Override
	public InvoiceLine calculateIgst(InvoiceLine invoiceline) {
		BigDecimal igst = invoiceline.getNetAmount().multiply(invoiceline.getGstRate()).divide(new BigDecimal(100));
		invoiceline.setIgst(igst);
		return invoiceline;
	}

	@Override
	public InvoiceLine calculateSgst(InvoiceLine invoiceline) {
		BigDecimal sgst = invoiceline.getNetAmount().multiply(invoiceline.getGstRate()).divide(new BigDecimal(200));
		invoiceline.setSgst(sgst);
		return invoiceline;
	}

	@Override
	public InvoiceLine calculateCgst(InvoiceLine invoiceline) {
		BigDecimal cgst = invoiceline.getNetAmount().multiply(invoiceline.getGstRate()).divide(new BigDecimal(200));
		invoiceline.setCgst(cgst);
		return invoiceline;
	}
	
	
	
	
	
	@Override
	public InvoiceLine calculateGrossAmount(InvoiceLine invoiceline) {

		System.err.println(invoiceline.getCgst());
		System.err.println(invoiceline.getIgst());
		System.err.println(invoiceline.getSgst());
		System.err.println(invoiceline.getNetAmount());

		BigDecimal gross = invoiceline.getNetAmount().add(invoiceline.getIgst()).add(invoiceline.getSgst())
				.add(invoiceline.getCgst());
			invoiceline.setGrossAmount(gross);
			return invoiceline;

	}
	
}