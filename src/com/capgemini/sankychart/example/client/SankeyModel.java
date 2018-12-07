package com.capgemini.sankychart.example.client;

import java.io.Serializable;

public class SankeyModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] from;

	private String[] to;

	private int[] weight;

	private String[] from_to;

	public SankeyModel() {
		super();
	}

	public SankeyModel(String[] from, String[] to, int[] weight, String[] from_to) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
		this.from_to = from_to;
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

	public String[] getFrom_to() {
		return from_to;
	}

	public void setFrom_to(String[] from_to) {
		this.from_to = from_to;
	}

}
