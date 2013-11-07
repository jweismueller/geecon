package com.prodyna.academy.geecon.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		TypedQuery<Conference> query = em.createNamedQuery("listConferences", Conference.class);
		List<Conference> resultList = query.getResultList();
		if (resultList.size() > 0) {
			return;
		}
		orgaData();
		conferenceData1();
		conferenceData2();
		log.info("Added test-data to database.");
	}

	private List<Speaker> speaker = new ArrayList<>();

	private List<Room> rooms = new ArrayList<>();

	private void conferenceData1() {
		Conference c = new Conference();
		c.setTitle("JAX");
		c.setDateFrom(CalendarUtil.getCalendar(2013, 3, 4));
		c.setDateTill(CalendarUtil.getCalendar(2013, 3, 6));
		c.setDescription("JAX is the conference for technical expertise within the Java and web environment. At JAX, the top experts from across Europe share their tried and tested knowledge with attendees.");
		em.persist(c);
		{
			Talk t = new Talk();
			t.setDateOn(CalendarUtil.getCalendar(2013, 3, 4));
			t.setTimeFrom("11:30");
			t.setTimeTill("13:00");
			t.setTitle("Keynote JEE");
			t.setDescription("The Keynote is introduced by your friendly host Arun Gupta (Java EE and GlassFish Evangelist) and features talks by Hasan Rizvi (Executive VP Oracle Fusion Middleware and Java), Cameron Purdy (VP Development @ Oracle) and Linda DeMichiel (Spec Lead for Java EE 7 Platform).");
			t.setConference(c);
			t.setRoom(rooms.get(0));
			SpeakerAssignment sp = new SpeakerAssignment();
			sp.setSpeaker(speaker.get(1));
			t.getAssignments().add(sp);
			em.persist(t);
		}
	}

	private void conferenceData2() {
		Conference c = new Conference();
		c.setTitle("JEECon");
		c.setDateFrom(CalendarUtil.getCalendar(2013, 5, 15));
		c.setDateTill(CalendarUtil.getCalendar(2013, 5, 17));
		c.setDescription("Java-based technologies, dynamic languages, RIA, enterprise architectures, patterns, distributed computing, software craftsmanship and much more... ");
		em.persist(c);
		{
			Talk t = new Talk();
			t.setDateOn(CalendarUtil.getCalendar(2013, 5, 15));
			t.setTimeFrom("09:00");
			t.setTimeTill("10:30");
			t.setTitle("JEE Power Workshop");
			t.setDescription("This workshop sets out the principles and technical practices that enable rapid, incremental delivery of high quality, valuable new functionality to users. Through automation of the build, deployment, and testing process, and improved collaboration between developers, testers and operations, delivery teams can get changes released in a matter of hours–sometimes even minutes–no matter what the size of a project or the complexity of its code base.");
			t.setConference(c);
			t.setRoom(rooms.get(1));
			SpeakerAssignment sp = new SpeakerAssignment();
			sp.setSpeaker(speaker.get(0));
			t.getAssignments().add(sp);
			em.persist(t);
		}
		{
			Talk t = new Talk();
			t.setDateOn(CalendarUtil.getCalendar(2013, 5, 15));
			t.setTimeFrom("11:30");
			t.setTimeTill("13:00");
			t.setTitle("Enterprise Integration Agility");
			t.setDescription("Today’s interconnected world requires that organizations rapidly deliver flexible-integrated solutions. The conventional approach is to integrate heterogeneous applications using web services but unfortunately that tends to tightly couple those applications. In this session we will explore several alternatives for achieving Enterprise Integration Agility.");
			t.setConference(c);
			t.setRoom(rooms.get(0));
			SpeakerAssignment sp = new SpeakerAssignment();
			sp.setSpeaker(speaker.get(1));
			t.getAssignments().add(sp);
			em.persist(t);
		}
		{
			Talk t = new Talk();
			t.setDateOn(CalendarUtil.getCalendar(2013, 5, 16));
			t.setTimeFrom("11:30");
			t.setTimeTill("13:00");
			t.setTitle("RESTful Imaginarium");
			t.setDescription("In this RESTful Imaginarium you will learn about about the core concepts of REST demonstrated through leading RESTful web service frameworks, Jersey (JAX-RS), Restlet, Spring MVC and NetKernel. During this daydream you will learn about the fallacies of URL parameters, the debate of PUT vs. POST and the power of HATEOAS.");
			t.setConference(c);
			t.setRoom(rooms.get(1));
			SpeakerAssignment sp1 = new SpeakerAssignment();
			sp1.setSpeaker(speaker.get(2));
			t.getAssignments().add(sp1);
			SpeakerAssignment sp2 = new SpeakerAssignment();
			sp2.setSpeaker(speaker.get(3));
			t.getAssignments().add(sp2);
			em.persist(t);
		}
		{
			Talk t = new Talk();
			t.setDateOn(CalendarUtil.getCalendar(2013, 5, 16));
			t.setTimeFrom("14:30");
			t.setTimeTill("16:00");
			t.setTitle("Architecture and Documentation");
			t.setDescription("Software architecture—the conceptual glue that holds every phase of a project together for its many stakeholders—is widely recognized as a critical element in modern software development. Practitioners have increasingly discovered that close attention to a software system’s architecture pays valuable dividends. Without an architecture that is appropriate for the problem being solved, a project will stumble along or, most likely, fail. Even with a superb architecture, if that architecture is not well understood or well communicated the project is unlikely to succeed.");
			t.setConference(c);
			t.setRoom(rooms.get(1));
			SpeakerAssignment sp2 = new SpeakerAssignment();
			sp2.setSpeaker(speaker.get(3));
			t.getAssignments().add(sp2);
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
			s.setName("Arun Gupta");
			em.persist(s);
			speaker.add(s);
		}
		{
			Speaker s = new Speaker();
			s.setName("Gernot Starke");
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
			r.setTitle("Beijing");
			r.setCapacity(300);
			em.persist(r);
			rooms.add(r);
		}
		{
			Room r = new Room();
			r.setTitle("Lagos");
			r.setCapacity(200);
			em.persist(r);
			rooms.add(r);
		}
		{
			Room r = new Room();
			r.setTitle("Moscow");
			r.setCapacity(100);
			em.persist(r);
			rooms.add(r);
		}
	}
}
