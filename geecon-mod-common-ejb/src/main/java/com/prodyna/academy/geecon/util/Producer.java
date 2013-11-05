package com.prodyna.academy.geecon.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {

	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember()
				.getDeclaringClass());
	}

}
