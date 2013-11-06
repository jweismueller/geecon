package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.academy.geecon.domain.Conference;
import com.prodyna.academy.geecon.domain.Talk;

@Path("/conferences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ConferenceService {

	@GET
	@Path("/ping")
	public String ping();

	@GET
	@Path("/")
	public List<Conference> listConferences();

	@GET
	@Path("/{cId}")
	public Conference getConference(@PathParam("cId") long id);

	@GET
	@Path("/{cId}/talks")
	public List<Talk> listTalks(@PathParam("cId") long id);

	@GET
	@Path("/{cId}/talks/{tId}")
	public Talk getTalk(@PathParam("cId") long cId, @PathParam("tId") long tId);

	@POST
	@Path("/")
	public void save(Conference conference);

	@POST
	@Path("/{cId}/talks")
	public void save(@PathParam("cId") long cId, Talk talk);
}
