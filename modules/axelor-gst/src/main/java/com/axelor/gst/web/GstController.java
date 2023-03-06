package com.axelor.gst.web;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.axelor.db.JPA;
import com.axelor.db.JpaSupport;
import com.axelor.gst.db.Contact;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.axelor.gst.service.GstService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

public class GstController extends JpaSupport {

	@Inject
	GstService gs;

	public void say(ActionRequest request, ActionRequest response) {

		System.err.println(gs.hello());
	}

	public void sequence(ActionRequest request, ActionResponse response) {

		Context context = request.getContext();
		Sequence sequence = context.asType(Sequence.class);
		if (sequence.getId() != null) {
			sequence = Beans.get(SequenceRepository.class).find(sequence.getId());
			gs.SequenceGenerator(sequence.getPadding(), sequence.getPrefix(), sequence.getSuffix());
			response.setAlert("Next Number is : " + sequence.getNextNumber());

			System.err.println(sequence.getNextNumber());
		}
	}

	public void getPrimaryPhone(ActionRequest request, ActionResponse response) {
		Context context = request.getContext();
		Invoice invoice = context.asType(Invoice.class);

		EntityManager em = getEntityManager();
		Query q1 = JPA.em().createQuery("SELECT primary_phone FROM GST_CONTACT WHERE name =:name");

		q1.setParameter("name", invoice.getParty());
		System.err.println(q1.setParameter("name", invoice.getParty()));
	}

}
