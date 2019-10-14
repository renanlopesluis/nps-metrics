package com.ilegra.npsmetrics.model;

import java.time.LocalDateTime;

public class AccessDataRow extends DataRow{

	private String url;
	private LocalDateTime dateTime;
	private User user;
	private Region region;
	
	public AccessDataRow(String url, LocalDateTime dateTime, User user, Region region) {
		super();
		this.url = url;
		this.dateTime = dateTime;
		this.user = user;
		this.region = region;
	}

	public String getUrl() {
		return url;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public User getUser() {
		return user;
	}
	
	public Region getRegion() {
		return region;
	}
	
}

