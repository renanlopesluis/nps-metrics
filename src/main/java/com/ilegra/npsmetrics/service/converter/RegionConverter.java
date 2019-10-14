package com.ilegra.npsmetrics.service.converter;

import com.ilegra.npsmetrics.enumeration.RegionEnum;
import com.ilegra.npsmetrics.exception.EnumNotFoundException;
import com.ilegra.npsmetrics.model.Region;

public class RegionConverter implements Converter<Region>{

	@Override
	public Region convert(String row) throws EnumNotFoundException {
		return new Region(row, RegionEnum.getFromCode(row).getDescripton());
	}

}
