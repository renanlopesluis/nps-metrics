package com.ilegra.npsmetrics.service.converter;

import com.ilegra.npsmetrics.enumeration.ConverterTypeEnum;
import com.ilegra.npsmetrics.enumeration.DataRowTypeEnum;
import com.ilegra.npsmetrics.model.DataRow;

public class LogAccessConverter implements Converter<DataRow>{
	
	@Override
	public DataRow convert(String row) throws Exception{
		ConverterTypeEnum converterType = ConverterTypeEnum.getFromType(DataRowTypeEnum.ACCESS_DATA_ROW);
		return converterType.getConverter().convert(row);
	}

}
