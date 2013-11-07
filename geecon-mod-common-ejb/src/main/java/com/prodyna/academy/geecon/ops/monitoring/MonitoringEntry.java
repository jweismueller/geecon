package com.prodyna.academy.geecon.ops.monitoring;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class MonitoringEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String service;

	private final String method;

	private long count;

	private long sum;

	private long minTime = Long.MAX_VALUE;

	private long maxTime = Long.MIN_VALUE;

	@ConstructorProperties({ "service", "method" })
	public MonitoringEntry(String service, String method) {
		this.service = service;
		this.method = method;
	}

	@ConstructorProperties({ "service", "method", "count", "sum", "minTime", "maxTime" })
	public MonitoringEntry(String service, String method, long count, long sum, long minTime, long maxTime) {
		this.service = service;
		this.method = method;
		this.count = count;
		this.sum = sum;
		this.minTime = minTime;
		this.maxTime = maxTime;
	}

	public void report(long time) {
		if (time < minTime) {
			minTime = time;
		}
		if (time > maxTime) {
			maxTime = time;
		}
		sum += time;
		count = count + 1;
	}

	public double getAverage() {
		double average;
		if (count == 0) {
			average = 0;
		} else {
			average = ((double) sum) / ((double) count);
		}
		return average;
	}

	public String getService() {
		return service;
	}

	public String getMethod() {
		return method;
	}

	public long getCount() {
		return count;
	}

	public long getSum() {
		return sum;
	}

	public long getMinTime() {
		return minTime;
	}

	public long getMaxTime() {
		return maxTime;
	}
}
