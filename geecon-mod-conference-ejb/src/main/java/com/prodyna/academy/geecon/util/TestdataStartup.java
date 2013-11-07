package com.prodyna.academy.geecon.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Conference;
import com.prodyna.academy.geecon.domain.Room;
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
		orgaData();
		conferenceData();
		log.info("success");
	}

	private List<Speaker> speaker = new ArrayList<>();

	private List<Room> rooms = new ArrayList<>();

	private void conferenceData() {
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
			t.setRoom(rooms.get(0));
			SpeakerAssignment sp = new SpeakerAssignment();
			sp.setSpeaker(speaker.get(0));
			t.getAssignments().add(sp);
			em.persist(t);
		}
	}

	private void orgaData() {
		{
			Speaker s = new Speaker();
			s.setName("Adam Bien");
			em.persist(s);
			speaker.add(s);
		}
		{
			Speaker s = new Speaker();
			s.setName("Stefan Tilkov");
			em.persist(s);
			speaker.add(s);
		}
		{
			Room r = new Room();
			r.setTitle("Entenhausen");
			r.setCapacity(200);
			em.persist(r);
			rooms.add(r);
		}
		{
			Room r = new Room();
			r.setTitle("XXL Lounge");
			r.setCapacity(200);
			em.persist(r);
			rooms.add(r);
		}
	}
}
