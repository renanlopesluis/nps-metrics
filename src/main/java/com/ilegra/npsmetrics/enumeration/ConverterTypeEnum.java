package com.ilegra.npsmetrics.enumeration;

import java.util.Arrays;
import java.util.List;

import com.ilegra.npsmetrics.exception.EnumNotFoundException;
import com.ilegra.npsmetrics.service.converter.AccessDataRowConverter;
import com.ilegra.npsmetrics.service.converter.DataRowConverter;

public enum ConverterTypeEnum {

	ACCESS_DATA_ROW_CONVERTER  (DataRowTypeEnum.ACCESS_DATA_ROW, new AccessDataRowConverter());
	
	private DataRowTypeEnum dataRowType;
	private DataRowConverter converter;
	
	private ConverterTypeEnum(DataRowTypeEnum dataRowType, DataRowConverter converter) {
		this.dataRowType = dataRowType;
		this.converter = converter;
	}
	
	public DataRowConverter getConverter() {
		return converter;
	}
	
	public static ConverterTypeEnum getFromType(DataRowTypeEnum dataRowType) throws EnumNotFoundException {
		List<ConverterTypeEnum> types = Arrays.asList(values());
		return types.stream().filter(x -> x.dataRowType.equals(dataRowType)).findFirst()
				.orElseThrow(() -> new EnumNotFoundException("Enum not found!"));
	}
}
