package com.prodyna.academy.geecon.util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Conference;

@Singleton
@Startup
public class TestdataStartup {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@PostConstruct
	private void startup() {
		Conference c = new Conference();
		c.setTitle("JEECon");
		em.persist(c);
		log.info("success");
	}
}
