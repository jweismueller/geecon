package com.prodyna.academy.geecon.ops.monitoring;

import java.lang.management.ManagementFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;

@Singleton
@Startup
public class MonitoringStartup {

	@Inject
	private Logger log;

	@PostConstruct
	private void registerMBeans() {
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName objectName = new ObjectName(MonitoringMXBean.OBJECT_NAME);
			ms.registerMBean(new Monitoring(), objectName);
		} catch (Exception e) {
			log.error("Unable to register MBeans");
		}
	}

	@PreDestroy
	private void unregisterMBeans() {
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName objectName = new ObjectName(MonitoringMXBean.OBJECT_NAME);
			ms.unregisterMBean(objectName);
		} catch (Exception e) {
			log.error("Unable to unregister MBeans");
		}
	}
}
