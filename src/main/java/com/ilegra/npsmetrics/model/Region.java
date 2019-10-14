package com.ilegra.npsmetrics.model;

public class Region extends DataRow{
	
	private String code;
	private String description;
	
	public Region(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
}
