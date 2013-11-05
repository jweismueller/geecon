package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.prodyna.academy.geecon.domain.Conference;

@Stateless
public class ConferenceServiceBean implements ConferenceService {

	@Inject
	private EntityManager em;

	public String info() {
		return this.getClass().getName();
	}

	@Override
	public List<Conference> list() {
		TypedQuery<Conference> query = em.createNamedQuery("listConference", Conference.class);
		return query.getResultList();
	}
}
