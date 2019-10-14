package com.ilegra.npsmetrics.model;

public class RankedAccessPerDayWeekYear extends Metric{

	private Integer day;
	private String dayOfWeek;
	private Integer year;
	
	public RankedAccessPerDayWeekYear(Long numberOfAccesses, Integer day, String dayOfWeek, Integer year) {
		super(numberOfAccesses);
		this.day = day;
		this.dayOfWeek = dayOfWeek;
		this.year = year;
	}

	public Integer getDay() {
		return day;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public Integer getYear() {
		return year;
	}
}
