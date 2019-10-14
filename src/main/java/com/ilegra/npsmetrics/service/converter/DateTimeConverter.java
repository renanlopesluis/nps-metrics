package com.ilegra.npsmetrics.service.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class DateTimeConverter implements Converter<LocalDateTime>{

	private static final int BEGIN_INDEX = 0;
	private static final int FINAL_INDEX = 10;
	
	@Override
	public LocalDateTime convert(String row) throws Exception {
		Long timestamp = Long.parseLong(row.substring(BEGIN_INDEX, FINAL_INDEX));
		return LocalDateTime.ofInstant(
				Instant.ofEpochSecond(timestamp), 
				TimeZone.getDefault().toZoneId());
	}
	
}
