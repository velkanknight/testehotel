package com.rfsystems.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {

	private Double adult;
	private Double child;


	public Double getAdult() {
		return adult;
	}

	public void setAdult(Double adult) {
		this.adult = adult;
	}

	public Double getChild() {
		return child;
	}

	public void setChild(Double child) {
		this.child = child;
	}
}
