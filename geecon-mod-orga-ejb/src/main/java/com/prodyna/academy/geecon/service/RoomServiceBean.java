package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Room;
import com.prodyna.academy.geecon.ops.logging.Logged;

@Logged
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

	@Override
	public List<Room> listRooms() {
		TypedQuery<Room> query = em.createNamedQuery("listRooms", Room.class);
		return query.getResultList();
	}

	@Override
	public Room getRoom(long id) {
		return em.find(Room.class, id);
	}
}
