package com.ilegra.service.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

import com.ilegra.npsmetrics.model.AccessDataRow;
import com.ilegra.npsmetrics.model.DataRow;
import com.ilegra.npsmetrics.model.Region;
import com.ilegra.npsmetrics.model.User;
import com.ilegra.npsmetrics.service.converter.AccessDataRowConverter;
import com.ilegra.npsmetrics.service.converter.Converter;
import com.ilegra.npsmetrics.service.converter.DateTimeConverter;
import com.ilegra.npsmetrics.service.converter.RegionConverter;
import com.ilegra.npsmetrics.service.converter.UserConverter;

public class ConverterTest {
	
	@Test
	public void shouldConvertIntoUser() throws Exception{
		Converter<User> converter = new UserConverter();
		checkUserAsserts(converter.convert("1037825323957 5b019db5-b3d0-46d2-9963-437860af707f"));
	}
	
	@Test
	public void shouldConvertIntoDateTime() throws Exception{
		Converter<LocalDateTime> converter = new DateTimeConverter();
		checkLocalDateTimeAsserts(converter.convert("1037825323957"));
	}

	@Test
	public void shouldConvertIntoRegion() throws Exception{
		Converter<Region> converter = new RegionConverter();
		checkRegionAsserts(converter.convert("1"));
	}
	
	@Test
	public void shouldConvertIntoAccessDataRow() throws Exception{
		Converter<DataRow> converter = new AccessDataRowConverter();
		checkAccessDataRowAsserts((AccessDataRow) converter.convert("/pets/exotic/cats/10 1037825323957 5b019db5-b3d0-46d2-9963-437860af707f 1"));
	}
		
	private void checkUserAsserts(User user) {
		Assert.assertEquals("957", user.getId());
		Assert.assertEquals("5b019db5-b3d0-46d2-9963-437860af707f", user.getUuid());
	}
	
	private void checkLocalDateTimeAsserts(LocalDateTime dateTime) {
		LocalDateTime date = LocalDateTime.ofInstant(
				Instant.ofEpochSecond(Long.parseLong("1037825323")), 
				TimeZone.getDefault().toZoneId());
		Assert.assertEquals(date, dateTime);
	}
	
	private void checkRegionAsserts(Region region) {
		Assert.assertEquals("1", region.getCode());
		Assert.assertEquals("us-east-1",region.getDescription());
	}

	private void checkAccessDataRowAsserts(AccessDataRow row) {
		LocalDateTime date = LocalDateTime.ofInstant(
				Instant.ofEpochSecond(Long.parseLong("1037825323")), 
				TimeZone.getDefault().toZoneId());
		
		Assert.assertEquals("/pets/exotic/cats/10", row.getUrl());
		Assert.assertEquals("957", row.getUser().getId());
		Assert.assertEquals("5b019db5-b3d0-46d2-9963-437860af707f", row.getUser().getUuid());
		Assert.assertEquals(date, row.getDateTime());
		Assert.assertEquals("1", row.getRegion().getCode());
		Assert.assertEquals("us-east-1", row.getRegion().getDescription());
	}
}
