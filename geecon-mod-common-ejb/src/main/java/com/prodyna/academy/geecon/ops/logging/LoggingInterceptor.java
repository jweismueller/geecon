package com.prodyna.academy.geecon.ops.logging;

import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.AbstractEntity;

@Logged
@Interceptor
public class LoggingInterceptor {

	@Inject
	private Logger log;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		long start = System.currentTimeMillis();
		String params = parametersToString(ic.getParameters());
		Method method = ic.getMethod();
		try {
			Object proceed = ic.proceed();
			long time = System.currentTimeMillis() - start;
			log.info(method.getDeclaringClass().getSimpleName() + "." + method.getName() + params + " in " + time
					+ "ms");
			return proceed;
		} catch (Exception e) {
			long time = System.currentTimeMillis() - start;
			log.error(method.getDeclaringClass().getSimpleName() + "." + method.getName() + params + " in " + time
					+ "ms throw error: " + e.toString());
			throw e;
		}
	}

	private String parametersToString(Object[] parameters) {
		StringBuilder out = new StringBuilder("(");
		for (int i = 0; i < parameters.length; i++) {
			Object o = parameters[i];
			if (i > 0) {
				out.append(", ");
			}
			if (o instanceof AbstractEntity) {
				out.append(o.getClass().getSimpleName()).append("[");
				out.append(((AbstractEntity) o).getId()).append("]");
			} else {
				out.append(String.valueOf(o));
			}
		}
		return out.append(")").toString();
	}
}
