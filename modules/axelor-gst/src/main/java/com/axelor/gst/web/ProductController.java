package com.axelor.gst.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.axelor.db.JpaSupport;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Product;
import com.axelor.gst.db.repo.ProductRepository;
import com.axelor.gst.service.ProductServiceImpl;
import com.axelor.inject.Beans;
import com.axelor.meta.schema.actions.ActionView;
import com.axelor.meta.schema.actions.ActionView.ActionViewBuilder;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.google.inject.Inject;

public class ProductController extends JpaSupport {

	public void setProductRecord(ActionRequest request, ActionResponse response) {

		if (request.getContext().get("_ids") == null) {
			return;
		}

		List<Long> requestIds = (List<Long>) request.getContext().get("_ids");

		response.setNotify("id is : " + requestIds);
		List<Product> productorderlist = null;
		System.err.println(requestIds);

		if (!requestIds.isEmpty()) {
			productorderlist = Beans.get(ProductRepository.class).all().filter("self.id in (?1)", requestIds).fetch();
		}
		System.err.println(productorderlist);
		List<InvoiceLine> invoicelinelist = new ArrayList<InvoiceLine>();

		for (int i = 0; i < productorderlist.size(); i++) {
			InvoiceLine invoiceline = Beans.get(InvoiceLine.class);
			invoiceline.setProduct(productorderlist.get(i));
			invoiceline.setGstRate(productorderlist.get(i).getGstRate());
			invoiceline.setPrice(productorderlist.get(i).getCostPrice());
			invoiceline.setItem(productorderlist.get(i).getName());
			invoiceline.setHsbn(productorderlist.get(i).getHsbn());
			System.err.println(productorderlist.get(i));
			invoicelinelist.add(invoiceline);

		}
		
//			for (Product p : productorderlist) {
//				InvoiceLine invoiceline = Beans.get(InvoiceLine.class);
//				invoiceline.setProduct(p);
//				invoiceline.setGstRate(p.getGstRate());
//			}
		
		System.err.println(invoicelinelist);

		ActionViewBuilder actionViewBuilder = ActionView.define("Invoice").model(Invoice.class.getName())
				.add("form", "form-invoice").domain("self.invoiceItems =:invoiceItems")
				.context("invoiceItems", invoicelinelist);

		response.setView(actionViewBuilder.map());

	}
}
