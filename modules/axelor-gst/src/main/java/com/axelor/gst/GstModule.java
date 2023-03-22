package com.axelor.gst;

import com.axelor.app.AxelorModule;
import com.axelor.gst.service.GstService;
import com.axelor.gst.service.GstServiceImpl;
import com.axelor.gst.service.ProductServiceImpl;
import com.axelor.gst.service.SequenceService;
import com.axelor.gst.service.SequenceServiceImpl;
import com.axelor.gst.service.productService;

public class GstModule extends AxelorModule {
	
	@Override
	protected void configure() {
		bind(GstService.class).to(GstServiceImpl.class);
		
		bind(SequenceService.class).to(SequenceServiceImpl.class);
		bind(productService.class).to(ProductServiceImpl.class);
	}
	
}
