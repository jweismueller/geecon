package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Speaker;
import com.prodyna.academy.geecon.ops.logging.Logged;

@Logged
@Stateless
public class SpeakerServiceBean implements SpeakerService {

	@Inject
	Logger log;

	@Inject
	private EntityManager em;

	@Override
	public String ping() {
		return this.getClass().getName();
	}

	@Override
	public List<Speaker> listSpeakers() {
		TypedQuery<Speaker> query = em.createNamedQuery("listSpeakers", Speaker.class);
		return query.getResultList();
	}

	@Override
	public Speaker getSpeaker(long id) {
		return em.find(Speaker.class, id);
	}
}
