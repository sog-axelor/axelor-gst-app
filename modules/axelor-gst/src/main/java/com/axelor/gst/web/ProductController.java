package com.axelor.gst.web;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.db.JpaSupport;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Product;
import com.axelor.gst.service.GstServiceImpl;
import com.axelor.meta.schema.actions.ActionView;
import com.axelor.meta.schema.actions.ActionView.ActionViewBuilder;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.google.inject.Inject;

public class ProductController extends JpaSupport {
	@Inject
	GstServiceImpl gi;

	public void ProductSelect(ActionRequest request, ActionResponse response) {
		Context context = request.getContext();

		System.err.println(request.getContext().entrySet());

		List<Integer> listId = (List<Integer>) request.getContext().get("_ids");
		System.err.println(listId);
		response.setNotify("You selected following id as " + listId);

		if (listId == null) {
			response.setNotify("Select Product First");
		}
		
		ActionViewBuilder builder = ActionView.define("Invoice").model(Invoice.class.getName()).add("form","form-invoice");
		builder.context("product_id", listId);
		builder.context("product", "test");
		
		Product product = context.asType(Product.class);
		if(product.getId()!=null) {
			
			//cgst = invoice.getInvoiceItems().stream().map(InvoiceLine::getCgst).reduce(BigDecimal.ZERO,BigDecimal::add);
			
			List<Integer> listId1 = (List<Integer>) request.getContext().get("_ids");
			for (Integer integer : listId1) {
				
			}
			
		}
		
		response.setView(builder.map());
		
		//response.setValue("invoiceLine", arraylist);
		

				/* Product product = context.asType(Product.class); */
				// ActionViewBuilder builder =
				// ActionView.define("Invoice").model(Invoice.class.getName());
				// System.err.println(Invoice.class.getName());
				// response.setView(builder.map());
				// response.setValue("product","test");
				// InvoiceLine invoiceline = context.asType(InvoiceLine.class);
				// invoiceline = gi.selectProductById(invoiceline);
				// response.setValue("product", invoiceline.getProduct());
				// System.err.println(invoiceline.getProduct());	

	}
}
