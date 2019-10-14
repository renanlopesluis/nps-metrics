package com.ilegra.npsmetrics.service.converter;

public interface Converter<T> {
	T convert(String row) throws Exception;
	
}