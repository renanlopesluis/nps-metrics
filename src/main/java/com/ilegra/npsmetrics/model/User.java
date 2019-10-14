package com.ilegra.npsmetrics.model;

public class User extends DataRow{

	private String id;
	private String uuid;

	public User(String id, String uuid) {
		this.id = id;
		this.uuid = uuid;
	}

	public String getId() {
		return id;
	}
	
	public String getUuid() {
		return uuid;
	}
		
}
