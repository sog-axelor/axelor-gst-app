package com.axelor.gst.web;

import java.util.List;

import com.axelor.db.JpaSupport;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.service.ProductServiceImpl;
import com.axelor.meta.schema.actions.ActionView;
import com.axelor.meta.schema.actions.ActionView.ActionViewBuilder;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.google.inject.Inject;

public class ProductController extends JpaSupport {
	@Inject
	ProductServiceImpl psi;
	
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

	
		response.setView(builder.map());
		
	

	}
}
