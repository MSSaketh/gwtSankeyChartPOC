package com.capgemini.sankychart.example.client;

import java.io.Serializable;

public class CSVmodel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] from;

	private String[] to;

	private int[] weight;

	public CSVmodel() {
		super();
	}

	public CSVmodel(String[] from, String[] to, int[] weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public String[] getFrom() {
		return from;
	}

	public void setFrom(String[] from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public int[] getWeight() {
		return weight;
	}

	public void setWeight(int[] weight) {
		this.weight = weight;
	}

}
