package com.ilegra.npsmetrics.model;

public abstract class DataRow {
	
	public boolean isClass(Class<?> classIdentity) {
		return this.getClass().equals(classIdentity);
	}
}