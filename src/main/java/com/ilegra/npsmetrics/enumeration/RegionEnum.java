package com.ilegra.npsmetrics.enumeration;

import java.util.Arrays;
import java.util.List;

import com.ilegra.npsmetrics.exception.EnumNotFoundException;

public enum RegionEnum {
	
	US_EAST_1	("1", "us-east-1"),
	US_WEST_2	("2", "us-west-2"),
	AP_SOUTH_1  ("3","ap-south-1");	
	
	private String code;
	private String description;
		
	private RegionEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getDescripton() {
		return description;
	}
	
	public static RegionEnum getFromCode(String code) throws EnumNotFoundException {
		List<RegionEnum> types = Arrays.asList(values());
		return types.stream().filter(x -> x.code.equals(code)).findFirst()
				.orElseThrow(() -> new EnumNotFoundException("Enum not found"));
	}
	
}
