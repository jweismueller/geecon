package com.prodyna.academy.geecon.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

@Stateless
public class RoomServiceBean implements RoomService {

	@Inject
	Logger log;

	@Inject
	private EntityManager em;

	@Override
	public String ping() {
		return this.getClass().getName();
	}
}
