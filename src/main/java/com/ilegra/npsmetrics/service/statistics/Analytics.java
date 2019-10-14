package com.ilegra.npsmetrics.service.statistics;

import java.util.List;

import com.ilegra.npsmetrics.model.Summary;

public interface Analytics {
	
	Summary summarize(List<String> lines) throws Exception;

}