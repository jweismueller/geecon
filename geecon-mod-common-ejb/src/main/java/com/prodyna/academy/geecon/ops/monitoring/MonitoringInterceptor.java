package com.prodyna.academy.geecon.ops.monitoring;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

import org.slf4j.Logger;

/**
 * Performance interceptrion for collecting performance date and publishing it via JMX.
 * 
 * @author Jürgen Weismüller, PRODYNA AG
 */
@Monitored
@Interceptor
public class MonitoringInterceptor {

	@Inject
	private Logger log;

	private MonitoringMXBean monitoringMBean;

	/**
	 * Default constructor which connects to the performance MXBean.
	 */
	public MonitoringInterceptor() {
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName objectName = new ObjectName(MonitoringMXBean.OBJECT_NAME);
			monitoringMBean = MBeanServerInvocationHandler.newProxyInstance(ms, objectName, MonitoringMXBean.class,
					false);
		} catch (Exception e) {
			log.error("Unable to register MBeans");
		}
	}

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		long start = System.currentTimeMillis();
		Object proceed = ic.proceed();
		Method method = ic.getMethod();
		monitoringMBean.report(method.getDeclaringClass().getName(), method.getName(), System.currentTimeMillis()
				- start);
		return proceed;
	}
}
