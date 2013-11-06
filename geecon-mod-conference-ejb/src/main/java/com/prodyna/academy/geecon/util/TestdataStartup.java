package com.prodyna.academy.geecon.util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Conference;
import com.prodyna.academy.geecon.domain.Speaker;
import com.prodyna.academy.geecon.domain.SpeakerAssignment;
import com.prodyna.academy.geecon.domain.Talk;

@Singleton
@Startup
public class TestdataStartup {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@PostConstruct
	private void startup() {
		Speaker s = new Speaker();
		s.setName("Adam Bien");
		em.persist(s);
		//
		Conference c = new Conference();
		c.setTitle("JEECon");
		c.setDateFrom(CalendarUtil.getCalendar(2013, 3, 4));
		c.setDateTill(CalendarUtil.getCalendar(2013, 3, 6));
		c.setDescription("Neuigkeiten aus der JEE Welt");
		em.persist(c);
		{
			Talk t = new Talk();
			t.setTimeFrom("11:30");
			t.setTimeTill("13:00");
			t.setTitle("Keynote JEE");
			t.setDescription("Keynote...");
			t.setConference(c);
			SpeakerAssignment sp = new SpeakerAssignment();
			sp.setSpeaker(s);
			t.getAssignments().add(sp);
			em.persist(t);
		}
		log.info("success");
	}
}
