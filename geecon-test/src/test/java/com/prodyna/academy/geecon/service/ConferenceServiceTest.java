package com.prodyna.academy.geecon.service;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Conference;

@RunWith(Arquillian.class)
public class ConferenceServiceTest {

	@Inject
	private Logger log;

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class)
				//
				// add named queries: addAsResource( new File("../geecon-mod-orga-ejb/.........") )
				//
				.addPackages(true, "com.prodyna.academy.geecon")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml")
				// datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml")
				// message queue
				.addAsWebInfResource("test-jms.xml", "test-jms.xml");
		return archive;
	}

	@Inject
	private ConferenceService conferenceService;

	@Test
	public void test() throws Exception {
		Assert.assertNotNull(conferenceService);
		Conference conference = conferenceService.getConference(1);
		log.info("Read conference: " + conference.getTitle());
	}
}
