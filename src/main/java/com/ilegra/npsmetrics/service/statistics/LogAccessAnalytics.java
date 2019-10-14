package com.ilegra.npsmetrics.service.statistics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.ilegra.npsmetrics.model.AccessDataRow;
import com.ilegra.npsmetrics.model.DataRow;
import com.ilegra.npsmetrics.model.RankedAccessPerDayWeekYear;
import com.ilegra.npsmetrics.model.RankedUrl;
import com.ilegra.npsmetrics.model.RankedUrlPerRegion;
import com.ilegra.npsmetrics.model.Summary;
import com.ilegra.npsmetrics.service.converter.DataRowListConverter;

public class LogAccessAnalytics<E> implements Analytics{
		
	@Override
	public Summary summarize(List<String> lines) throws Exception {
		List<DataRow> dataRows = new DataRowListConverter().convert(lines);
		List<AccessDataRow> accessRows = getAcessDataRowList(dataRows);
	
		return new Summary(getTopThreeMostAccessedURLs(accessRows),
				getTopThreeMostAccessedURLsPerRegion(accessRows),
				getTheLeastAccessedURLInTheWorld(accessRows),
				getTopThreeAccessesPerDayWeekYear(accessRows),
				getTheMostAccessedMinute(accessRows));
	}	
	
	private List<AccessDataRow> getAcessDataRowList(List<DataRow> rows) {
		return rows.stream().filter(x -> x.isClass(AccessDataRow.class))
				.map(x -> (AccessDataRow) x)
				.collect(Collectors.toList());
	}	
	
	private List<RankedUrl> getTopThreeMostAccessedURLs(List<AccessDataRow> rows) {
		List<RankedUrl> urls = groupByUrl(rows)
				.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .limit(3)
		        .map(e -> new RankedUrl(e.getKey(), e.getValue()))
		        .collect(Collectors.toList());
		return urls;
	}
	
	private List<RankedUrlPerRegion> getTopThreeMostAccessedURLsPerRegion(List<AccessDataRow> rows) {
		List<RankedUrlPerRegion> urls = groupByUrlAndRegion(rows)
				.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .limit(3)
		        .map(e -> new RankedUrlPerRegion(e.getKey().get(0), e.getValue(), e.getKey().get(1)))
		        .collect(Collectors.toList()); 
		return urls;
	}
	
	private List<RankedAccessPerDayWeekYear> getTopThreeAccessesPerDayWeekYear(List<AccessDataRow> rows) {
		List<RankedAccessPerDayWeekYear> urls = groupByPerDayWeekYear(rows)
				.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .limit(3)
		        .map(e -> new RankedAccessPerDayWeekYear(e.getValue(), (Integer) e.getKey().get(0), e.getKey().get(1).toString(), (Integer) e.getKey().get(2)))
		        .collect(Collectors.toList());  
		return urls;
	}
	
	private Integer getTheMostAccessedMinute(List<AccessDataRow> rows) {
		Map<Integer, Long> minutes = rows.stream().collect(
				Collectors.groupingBy(x->x.getDateTime().getMinute(), 
						Collectors.counting()));
		return Collections.max(minutes.entrySet(), Comparator.comparing(Entry::getValue)).getKey();
	}
	
	private String getTheLeastAccessedURLInTheWorld(List<AccessDataRow> rows) {
		Map<String, Long> urls = rows.stream().collect(
					Collectors.groupingBy(x->x.getUrl(), 
							Collectors.counting()));
		return Collections.min(urls.entrySet(), Comparator.comparing(Entry::getValue)).getKey();
		
	}	
	
	private Map<String, Long> groupByUrl(List<AccessDataRow> rows) {
		return rows.stream().collect(
					Collectors.groupingBy(x->x.getUrl(), 
							Collectors.counting()));
	}
	
	private Map<List<String>, Long> groupByUrlAndRegion(List<AccessDataRow> rows){
		return rows.stream().collect(Collectors.groupingBy(x->Arrays.
				asList(x.getUrl(), x.getRegion().getCode()), Collectors.counting()));
	}
	
	private Map<List<Object>,Long> groupByPerDayWeekYear(List<AccessDataRow> rows){
		return rows.stream().collect(Collectors.groupingBy(x->Arrays.
				asList(x.getDateTime().getDayOfMonth(), x.getDateTime().getDayOfWeek().toString(), x.getDateTime().getYear()), Collectors.counting()));
	}
}
