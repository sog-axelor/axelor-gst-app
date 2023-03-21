package com.axelor.gst.service;

import org.apache.commons.lang3.StringUtils;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.axelor.inject.Beans;

public class SequenceServiceImpl implements SequenceService {

	@Override
	public Sequence setNextNumber(Sequence sequence) {
		String padnext = "";
		String nextSeqeunce = "";
		
		if(sequence.getPadding() != null) {
			padnext = StringUtils.leftPad("0".toString(),sequence.getPadding(),"0");
		}
		nextSeqeunce  = calculateNextSequence(sequence, padnext);
		sequence.setNextNumber(nextSeqeunce);
		
		return sequence;
	}

	@Override
	public String calculateNextSequence(Sequence seq, String padnext) {
		String nextSeqeunce = "";
		
		if(seq.getPrefix()!= null)
			nextSeqeunce = seq.getPrefix() + padnext;
		if(seq.getSuffix()!=null)
			nextSeqeunce += seq.getSuffix();		
		return nextSeqeunce;
	}

	@Override
	public String getSequence(String modelName) throws Exception {
		
		Sequence sequence = Beans.get(SequenceRepository.class).all().filter("self.model.fullName = ?",modelName).fetchOne();
		String nextSequence = "";
		int l;
		
		if(sequence == null){
			throw new Exception("NO sequence is defined");
		}
		
		if(sequence != null) {
			String padding = "";
			if(sequence.getSuffix() != null)
				l = sequence.getNextNumber().length() - sequence.getSuffix().length();
			else
				l = sequence.getNextNumber().length();
			
			if(sequence.getPrefix()!=null) {
				for(int i = sequence.getPrefix().length(); i<l;i++) {
					padding += sequence.getNextNumber().charAt(i);
				}
			}
			
			Long nextVal = Long.parseLong(padding);
			nextVal++;
			String padNext = StringUtils.leftPad(nextVal.toString(),sequence.getPadding(),"0");
			nextSequence = calculateNextSequence(sequence, padNext);
			
			if(padNext.length() != sequence.getPadding()) {
				sequence.setPadding(padNext.length());
				sequence.setNextNumber(nextSequence);
				Beans.get(SequenceRepository.class).save(sequence);
			}
		}
		System.err.println("Next Sequence is  " +nextSequence  );
		return nextSequence;
	}

}
/*
 * Prefix + number of digits as per padding starting with 0 + Suffix "Example :
 * ABC0000, ABC0001,ABC0003 (Here Prefix: = ABC, Suffix = null, Padding = 4,
 * Increment on each call to get this number) "
 */