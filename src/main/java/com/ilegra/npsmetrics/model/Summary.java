package com.ilegra.npsmetrics.model;

import java.util.List;

public class Summary {

	private List<RankedUrl> topThreeMostAcessedURLsInTheWorld;
	private List<RankedUrlPerRegion> topThreeMostAcessedURLsPerRegion;
	private String theLeastAccessedUrlInTheWorld;
	private List<RankedAccessPerDayWeekYear> topThreeAccessesPerDayWeekYear;
	private Integer theMinuteWithMoreAcess;
	
	public Summary(List<RankedUrl> topThreeMostAcessedURLsInTheWorld,
			List<RankedUrlPerRegion> topThreeMostAcessedURLsPerRegion,
			String theLeastAccessedUrlInTheWorld,
			List<RankedAccessPerDayWeekYear> topThreeMostAcessedURLsPerDayWeekYear,
			Integer theMinuteWithMoreAcess) {
		super();
		this.topThreeMostAcessedURLsInTheWorld = topThreeMostAcessedURLsInTheWorld;
		this.topThreeMostAcessedURLsPerRegion = topThreeMostAcessedURLsPerRegion;
		this.theLeastAccessedUrlInTheWorld = theLeastAccessedUrlInTheWorld;
		this.topThreeAccessesPerDayWeekYear = topThreeMostAcessedURLsPerDayWeekYear;
		this.theMinuteWithMoreAcess = theMinuteWithMoreAcess;
	}

	public List<RankedUrl> getTopThreeMostAcessedURLsInTheWorld() {
		return topThreeMostAcessedURLsInTheWorld;
	}

	public List<RankedUrlPerRegion> getTopThreeMostAcessedURLsPerRegion() {
		return topThreeMostAcessedURLsPerRegion;
	}

	public String getTheLeastAccessedUrlInTheWorld() {
		return theLeastAccessedUrlInTheWorld;
	}

	public List<RankedAccessPerDayWeekYear> getTopThreeAccessesPerDayWeekYear() {
		return topThreeAccessesPerDayWeekYear;
	}

	public Integer getTheMinuteWithMoreAcess() {
		return theMinuteWithMoreAcess;
	}
	
}
