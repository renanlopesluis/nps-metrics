package com.ilegra.npsmetrics.restapi;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ilegra.npsmetrics.io.LogFileStream;
import com.ilegra.npsmetrics.model.Summary;
import com.ilegra.npsmetrics.service.statistics.Analytics;
import com.ilegra.npsmetrics.service.statistics.LogAccessAnalytics;

@Path("/laa")
public class LogAnalyticsRestAPI {
	
	private static final Logger logger = Logger.getLogger(LogAnalyticsRestAPI.class.getName());
		
	@Path("/metrics")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSummary() {
		try {
			Analytics analytics = new LogAccessAnalytics<>();
			Summary summary = analytics.summarize(new LogFileStream().read());
			return Response.ok(summary).build();
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Error while trying to get metrics",e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
