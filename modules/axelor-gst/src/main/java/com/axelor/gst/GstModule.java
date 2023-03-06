package com.axelor.gst;

import com.axelor.app.AxelorModule;
import com.axelor.gst.service.GstService;
import com.axelor.gst.service.GstServiceImpl;

public class GstModule extends AxelorModule {
	
	@Override
	protected void configure() {
		bind(GstService.class).to(GstServiceImpl.class);
	}
	
}
