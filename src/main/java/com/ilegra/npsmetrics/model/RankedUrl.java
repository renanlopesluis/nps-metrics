package com.ilegra.npsmetrics.model;

public class RankedUrl extends Metric{
	
	private String url;
		
	public RankedUrl(String url, Long numberOfAccesses) {
		super(numberOfAccesses);
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
