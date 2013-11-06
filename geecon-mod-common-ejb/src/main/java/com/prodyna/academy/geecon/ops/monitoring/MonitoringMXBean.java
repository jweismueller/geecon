package com.prodyna.academy.geecon.ops.monitoring;

import java.util.List;

public interface MonitoringMXBean {

	public static final String OBJECT_NAME = "com.prodyna.academy.geecon:service=Monitoring";

	void report(String service, String method, long time);

	List<MonitoringEntry> getAll();

	int getCount();

	MonitoringEntry getWorstMethodByTime();

	MonitoringEntry getWorstMethodByCount();

	void reset();

}
