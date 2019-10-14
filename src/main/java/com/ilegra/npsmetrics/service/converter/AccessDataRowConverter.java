package com.ilegra.npsmetrics.service.converter;

import java.time.LocalDateTime;

import com.ilegra.npsmetrics.model.AccessDataRow;
import com.ilegra.npsmetrics.model.DataRow;
import com.ilegra.npsmetrics.model.Region;
import com.ilegra.npsmetrics.model.User;

public class AccessDataRowConverter extends DataRowConverter{
	
	private static final int URL_INDEX = 0;
	private static final int TIMESTAMP_INDEX = 1;
	private static final int USER_INDEX = 2;
	private static final int REGION_INDEX = 3;
	
		
	public DataRow convert(String row) throws Exception {
		String[] data = row.split(SPLITTER);
		String url = new String(data[URL_INDEX]);
		
		LocalDateTime dateTime = new DateTimeConverter().convert(data[TIMESTAMP_INDEX]);
		User user = new UserConverter().convert(data[TIMESTAMP_INDEX] + CONCAT + data[USER_INDEX]);
		Region region = new RegionConverter().convert(data[REGION_INDEX]);
		return new AccessDataRow(url, dateTime, user, region);
	}
		
	

}
