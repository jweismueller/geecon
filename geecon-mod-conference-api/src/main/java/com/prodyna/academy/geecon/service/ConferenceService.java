package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.academy.geecon.domain.Conference;

@Path("/conferences")
public interface ConferenceService {

	@GET
	@Path("/ping")
	public String ping();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conference> list();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Conference getConference(@PathParam("id") long id);

}
