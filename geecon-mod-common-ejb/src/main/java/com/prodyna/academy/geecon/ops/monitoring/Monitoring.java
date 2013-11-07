package com.prodyna.academy.geecon.ops.monitoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monitoring implements MonitoringMXBean {

	private Map<String, MonitoringEntry> entries = new HashMap<String, MonitoringEntry>();

	@Override
	public void report(String service, String method, long time) {
		String key = service + ":" + method;
		MonitoringEntry entry = entries.get(key);
		if (entry == null) {
			entry = new MonitoringEntry(service, method);
			entries.put(key, entry);
		}
		entry.report(time);
	}

	@Override
	public List<MonitoringEntry> getAll() {
		List<MonitoringEntry> entriesList = new ArrayList<MonitoringEntry>(entries.values());
		return entriesList;
	}

	@Override
	public int getCount() {
		return entries.size();
	}

	@Override
	public MonitoringEntry getWorstByTime() {
		MonitoringEntry worstByTime = null;
		long worstTimeSum = -1;
		for (MonitoringEntry entry : entries.values()) {
			long sum = entry.getSum();
			if (sum > worstTimeSum) {
				worstTimeSum = sum;
				worstByTime = entry;
			}
		}
		return worstByTime;
	}

	@Override
	public MonitoringEntry getWorstByCount() {
		MonitoringEntry worstByCount = null;
		long worstCount = -1;
		for (MonitoringEntry entry : entries.values()) {
			long count = entry.getCount();
			if (count > worstCount) {
				worstCount = count;
				worstByCount = entry;
			}
		}
		return worstByCount;
	}

	@Override
	public void reset() {
		entries.clear();
	}
}
