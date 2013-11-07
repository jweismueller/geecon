package com.prodyna.academy.geecon.auditing;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Talk;
import com.prodyna.academy.geecon.messaging.TalkModifiedQueueAccess;
import com.prodyna.academy.geecon.util.CompareUtil;
import com.prodyna.academy.geecon.util.CompareUtil.ComapareResponse;

@Audited
@Interceptor
public class TalkModifiedInterceptor {

	@Inject
	Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private TalkModifiedQueueAccess queueAccess;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		for (Object param : ic.getParameters()) {
			if (param instanceof Talk) {
				Talk t = (Talk) param;
				if (t.getId() != null) {
					compareTalk(t);
				}
			}
		}
		Object proceed = ic.proceed();
		return proceed;
	}

	private void compareTalk(Talk t1) {
		Talk t2 = em.find(Talk.class, t1.getId());
		try {
			ComapareResponse compare = CompareUtil.compare(t1, t2);
			if (compare.hasChanges()) {
				StringBuilder msg = new StringBuilder();
				msg.append("Talk[" + t1.getId() + "]: ");
				msg.append(compare.getReport());
				queueAccess.send(msg.toString());
			}
		} catch (Exception e) {
			log.error("Error at Talk[" + t1.getId() + "]", e);
		}
	}
}
