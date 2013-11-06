package com.prodyna.academy.geecon.ops.monitoring;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class MonitoringEntry implements Serializable {
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Service calls.
	 */
	private final String service;

	/**
	 * Service method.
	 */
	private final String method;

	/**
	 * Invocation count.
	 */
	private long count;

	/**
	 * Accumulated processing time.
	 */
	private long sum;

	/**
	 * Best invocation time.
	 */
	private long minTime = Long.MAX_VALUE;

	/**
	 * Worst invocation time.
	 */
	private long maxTime = Long.MIN_VALUE;

	/**
	 * Constructor taking the service and method name.
	 * 
	 * @param service
	 *            Service name.
	 * @param method
	 *            Method name.
	 */
	@ConstructorProperties({ "service", "method" })
	public MonitoringEntry(String service, String method) {
		this.service = service;
		this.method = method;
	}

	/**
	 * Constructor taking pre defined performance data (needed for deserialisation of performance data).
	 * 
	 * @param service
	 *            Service name.
	 * @param method
	 *            Method name.
	 * @param count
	 *            Invocation count.
	 * @param sum
	 *            Accumulated processing time.
	 * @param minTime
	 *            Best invocation time.
	 * @param maxTime
	 *            Worst invocation time.
	 */
	@ConstructorProperties({ "service", "method", "count", "sum", "minTime", "maxTime" })
	public MonitoringEntry(String service, String method, long count, long sum, long minTime, long maxTime) {
		this.service = service;
		this.method = method;
		this.count = count;
		this.sum = sum;
		this.minTime = minTime;
		this.maxTime = maxTime;
	}

	/**
	 * Report new invocation time.
	 * 
	 * @param time
	 *            Processing time of invocation.
	 */
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

	/**
	 * Get average invocation time.
	 * 
	 * @return Average invocation time.
	 */
	public double getAverage() {
		double average;
		if (count == 0) {
			average = 0;
		} else {
			average = ((double) sum) / ((double) count);
		}

		return average;
	}

	/**
	 * Get the service
	 * 
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * Get the method
	 * 
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Get the count
	 * 
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * Get the sum
	 * 
	 * @return the sum
	 */
	public long getSum() {
		return sum;
	}

	/**
	 * Get the minTime
	 * 
	 * @return the minTime
	 */
	public long getMinTime() {
		return minTime;
	}

	/**
	 * Get the maxTime
	 * 
	 * @return the maxTime
	 */
	public long getMaxTime() {
		return maxTime;
	}
}
