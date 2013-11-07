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

@RunWith(Arquillian.class)
public class ConferenceServiceTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class).addPackages(true, "com.prodyna.academy.geecon")
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
	}
}
