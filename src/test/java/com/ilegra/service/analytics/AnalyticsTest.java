package com.ilegra.service.analytics;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ilegra.npsmetrics.model.Summary;
import com.ilegra.npsmetrics.service.statistics.Analytics;
import com.ilegra.npsmetrics.service.statistics.LogAccessAnalytics;

public class AnalyticsTest {
	
	private List<String> data;
	private Analytics analytics;
	
	public AnalyticsTest() {
		analytics = new LogAccessAnalytics<>();
		data = new ArrayList<String>();
	}
	
	@Before
	public void init() {
		data.add("/pets/exotic/cats/10 1037825323957 5b019db5-b3d0-46d2-9963-437860af707f 1");
		data.add("/pets/guaipeca/dogs/1 1037825323957 5b019db5-b3d0-46d2-9963-437860af707g 2");
		data.add("/tiggers/bid/now 1037825323957 5b019db5-b3d0-46d2-9963-437860af707e 3");
		data.add("/pets/exotic/cats/10 1037825323957 5b019db5-b3d0-46d2-9963-437860af707f 2");
		data.add("/pets/exotic/cats/10 1037825323957 5b019db5-b3d0-46d2-9963-437860af707f 2");
		data.add("/tiggers/bid/now 1037825323957 5b019db5-b3d0-46d2-9963-437860af707e 1");
		data.add("/dogs/bid/now 1037825323957 5b019db5-b3d0-46d2-9963-437860af707e 3");
	}
	
	@Test
	public void shouldSummarizeData() throws Exception {
		Summary summary = analytics.summarize(data);
		checkSummaryAsserts(summary);
		
	}
	
	private void checkSummaryAsserts(Summary summary) {
		Assert.assertNotNull(summary);
		checkTopThreeTheMostAccessedUrls(summary);
		checkTopThreeTheMostAccessedUrlsPerRegion(summary);
		checkTheMinuteWithMoreAccess(summary);
		checkTheTopThreeAccessesPerDayWeekYear(summary);
		
	}
	
	private void checkTopThreeTheMostAccessedUrls(Summary summary) {
		Assert.assertEquals(summary.getTopThreeMostAcessedURLsInTheWorld().get(0).getUrl(), "/pets/exotic/cats/10");
		Assert.assertEquals(summary.getTopThreeMostAcessedURLsInTheWorld().get(1).getUrl(), "/tiggers/bid/now");
		Assert.assertEquals(summary.getTopThreeMostAcessedURLsInTheWorld().get(2).getUrl(), "/pets/guaipeca/dogs/1");
		Assert.assertTrue(summary.getTopThreeMostAcessedURLsInTheWorld().get(0).getNumberOfAccesses().equals(3L));
		Assert.assertTrue(summary.getTopThreeMostAcessedURLsInTheWorld().get(1).getNumberOfAccesses().equals(2L));
		Assert.assertTrue(summary.getTopThreeMostAcessedURLsInTheWorld().get(2).getNumberOfAccesses().equals(1L));
	}
	
	private void checkTopThreeTheMostAccessedUrlsPerRegion(Summary summary) {
		Assert.assertEquals(summary.getTopThreeMostAcessedURLsPerRegion().get(0).getUrl(), "/pets/exotic/cats/10");
		Assert.assertEquals(summary.getTopThreeMostAcessedURLsPerRegion().get(1).getUrl(), "/dogs/bid/now");
		Assert.assertEquals(summary.getTopThreeMostAcessedURLsPerRegion().get(2).getUrl(), "/pets/guaipeca/dogs/1");
		Assert.assertTrue(summary.getTopThreeMostAcessedURLsPerRegion().get(0).getNumberOfAccesses().equals(2L));
		Assert.assertTrue(summary.getTopThreeMostAcessedURLsPerRegion().get(1).getNumberOfAccesses().equals(1L));
		Assert.assertTrue(summary.getTopThreeMostAcessedURLsPerRegion().get(2).getNumberOfAccesses().equals(1L));
	}
	
	private void checkTheMinuteWithMoreAccess(Summary summary) {
		LocalDateTime dateTime = getLocalDateTime();
		Assert.assertTrue(summary.getTheMinuteWithMoreAcess().equals(dateTime.getMinute()));
	}
	
	private void checkTheTopThreeAccessesPerDayWeekYear(Summary summary) {
		LocalDateTime dateTime = getLocalDateTime();
		Assert.assertEquals(summary.getTheLeastAccessedUrlInTheWorld(), "/pets/guaipeca/dogs/1");
		Assert.assertTrue(summary.getTopThreeAccessesPerDayWeekYear().get(0).getDay().equals(dateTime.getDayOfMonth()));
		Assert.assertEquals(summary.getTopThreeAccessesPerDayWeekYear().get(0).getDayOfWeek(), dateTime.getDayOfWeek().toString());
		Assert.assertTrue(summary.getTopThreeAccessesPerDayWeekYear().get(0).getYear().equals(dateTime.getYear()));
		Assert.assertTrue(summary.getTopThreeAccessesPerDayWeekYear().get(0).getNumberOfAccesses().equals(7L));

	}
	
	private LocalDateTime getLocalDateTime() {
		return LocalDateTime.ofInstant(
				Instant.ofEpochSecond(Long.parseLong("1037825323")), 
				TimeZone.getDefault().toZoneId());
	}
}
