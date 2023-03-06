package com.axelor.gst.service;

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

}
