package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.auditing.Audited;
import com.prodyna.academy.geecon.domain.Conference;
import com.prodyna.academy.geecon.domain.Talk;
import com.prodyna.academy.geecon.ops.logging.Logged;
import com.prodyna.academy.geecon.ops.monitoring.Monitored;

@Logged
@Monitored
@Stateless
public class ConferenceServiceBean implements ConferenceService {

	@Inject
	private Logger log;

	@Inject
	private ConferenceValidator conferenceValidator;

	@Inject
	private EntityManager em;

	@Override
	public String ping() {
		return this.getClass().getName();
	}

	@Override
	public List<Conference> listConferences() {
		TypedQuery<Conference> query = em.createNamedQuery("listConferences", Conference.class);
		return query.getResultList();
	}

	@Override
	public Conference getConference(long cId) {
		Conference c = em.find(Conference.class, cId);
		if (c == null) {
			throw new EntityNotFoundException();
		} else {
			return c;
		}
	}

	@Override
	public void save(Conference conference) {
		em.merge(conference);
	}

	@Override
	public void update(Conference conference) {
		log.info("got update");
		em.merge(conference);
	}

	@Override
	public List<Talk> listTalks(long cId) {
		TypedQuery<Talk> query = em.createNamedQuery("listTalks", Talk.class);
		query.setParameter("cId", cId);
		return query.getResultList();
	}

	@Override
	public Talk getTalk(long cId, long tId) {
		Talk t = em.find(Talk.class, tId);
		if (t == null) {
			throw new EntityNotFoundException();
		} else {
			return t;
		}
	}

	@Override
	@Audited
	public void save(long cId, Talk talk) {
		Conference c = getConference(cId);
		conferenceValidator.validate(c, talk);
		if (talk.getId() == null) {
			talk.setConference(c);
			em.persist(talk);
		} else {
			em.merge(talk);
		}
	}

	@Override
	public List<Talk> listTalksForRoom(long cId, long rId) {
		TypedQuery<Talk> query = em.createNamedQuery("listTalksForRoom", Talk.class);
		query.setParameter("cId", cId);
		query.setParameter("rId", rId);
		return query.getResultList();
	}

	@Override
	public List<Talk> listTalksForSpeaker(long cId, long sId) {
		TypedQuery<Talk> query = em.createNamedQuery("listTalksForSpeaker", Talk.class);
		query.setParameter("cId", cId);
		query.setParameter("sId", sId);
		return query.getResultList();
	}
}
