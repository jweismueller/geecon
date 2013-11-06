package com.prodyna.academy.geecon.ops.logging;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

@Logged
@Interceptor
public class LoggingInterceptor {

	@Inject
	private Logger log;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		Object proceed = ic.proceed();
		log.info(" >>> ");
		return proceed;
	}
}
