package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Conference;
import com.prodyna.academy.geecon.domain.Talk;

@Stateless
public class ConferenceServiceBean implements ConferenceService {

	@Inject
	Logger log;

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
	public List<Talk> listTalks(long cId) {
		TypedQuery<Talk> query = em.createNamedQuery("listTalks", Talk.class);
		query.setParameter("cId", cId);
		return query.getResultList();
	}

	@Override
	public Talk getTalk(long cId, long tId) {
		Talk t = em.find(Talk.class, cId);
		if (t == null) {
			throw new EntityNotFoundException();
		} else {
			return t;
		}
	}

	@Override
	public void save(Conference conference) {
		em.merge(conference);
	}

	@Override
	public void save(long cId, Talk talk) {
		em.merge(talk);
	}
}
