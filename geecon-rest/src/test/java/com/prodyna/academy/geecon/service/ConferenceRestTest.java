package com.prodyna.academy.geecon.service;

import java.net.URL;
import java.util.List;

import org.jboss.resteasy.client.ProxyFactory;
import org.junit.Assert;
import org.junit.Test;

import com.prodyna.academy.geecon.domain.Conference;

public class ConferenceRestTest {

	@Test
	public void testConferenceRestService() throws Exception {
		String p = System.getProperty("geecon-rest-url", "http://localhost:8080/geecon-rest/");
		URL baseUrl = new URL(p);
		ConferenceService conferenceService = ProxyFactory.create(ConferenceService.class, baseUrl.toString());
		Assert.assertNotNull(conferenceService.ping());
		List<Conference> conferences = conferenceService.listConferences();
		for (Conference conference : conferences) {
			System.out.println(conference.getTitle());
		}
	}
}
