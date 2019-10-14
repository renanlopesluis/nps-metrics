package com.ilegra.npsmetrics.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.ilegra.npsmetrics.model.DataRow;

public class DataRowListConverter {
	
	public List<DataRow> convert(List<String> lines) throws Exception{
		List<DataRow> dataRows = new ArrayList<>();
		Converter<DataRow> converter = new LogAccessConverter();
		for (String line : lines) {
			DataRow row = converter.convert(line);
			dataRows.add(row);
		}
		return dataRows;
	}

}
