package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.academy.geecon.domain.Room;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RoomService {

	@GET
	@Path("/ping")
	public String ping();

	@GET
	@Path("/")
	public List<Room> listRooms();

	@GET
	@Path("/{rId}")
	public Room getRoom(@PathParam("rId") long id);

	@POST
	@Path("/")
	public void save(Room room);
}
