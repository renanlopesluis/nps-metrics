package com.ilegra.npsmetrics.model;

public class RankedUrlPerRegion extends RankedUrl {
	
	private String regionCode;

	public RankedUrlPerRegion(String url, Long numberOfAccesses, String regionCode) {
		super(url, numberOfAccesses);
		this.regionCode = regionCode;

	}

	public String getRegionCode() {
		return regionCode;
	}
}
