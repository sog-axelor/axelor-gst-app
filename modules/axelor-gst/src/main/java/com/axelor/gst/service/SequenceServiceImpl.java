package com.axelor.gst.service;

import org.apache.commons.lang3.StringUtils;
import com.google.inject.persist.Transactional;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.axelor.inject.Beans;

public class SequenceServiceImpl implements SequenceService {

	@Override
    public Sequence setNextNumber(Sequence sequence) {

        String padnext = "";
        String nextSequence = "";

        if (sequence.getPadding() != null) {
            padnext = StringUtils.leftPad("0".toString(), sequence.getPadding(), "0");
        }
        nextSequence = calculateNextSequence(sequence, padnext);
        sequence.setNextNumber(nextSequence);
        return sequence;
    }

	 public String calculateNextSequence(Sequence seq, String padnext) {

	        String nextSequence = "";

	        if (seq.getPrefix() != null)
	            nextSequence = seq.getPrefix() + padnext;
	        if (seq.getSuffix() != null)
	            nextSequence += seq.getSuffix();
	        return nextSequence;
	    }

	  	@Override
	    @Transactional
	    public String getSequence(String modelName) throws Exception {
	        Sequence seq = Beans.get(SequenceRepository.class).all().filter("self.model.name = ?", modelName).fetchOne();

	        if (seq == null) {
	            throw new Exception("No sequence defined");
	        }
	        String nextSequence = "";
	        int l;

	        if (seq != null) {
	            String padding = "";
	            if (seq.getSuffix() != null)
	                l = seq.getNextNumber().length() - seq.getSuffix().length();
	            else
	                l = seq.getNextNumber().length();
	            if (seq.getPrefix() != null) {
	                for (int i = seq.getPrefix().length(); i < l; i++) {
	                    padding += seq.getNextNumber().charAt(i);
	                }
	            }
	            Long nextVal = Long.parseLong(padding);
	            nextVal++;
	            String padnext = StringUtils.leftPad(nextVal.toString(), seq.getPadding(), "0");
	            nextSequence = calculateNextSequence(seq, padnext);
	            if (padnext.length() != seq.getPadding())
	                seq.setPadding(padnext.length());
	            	seq.setNextNumber(nextSequence);
	            	Beans.get(SequenceRepository.class).save(seq);
	        	}
	        	return nextSequence;
	    }
}
