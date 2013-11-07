package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.academy.geecon.domain.Speaker;

@Path("/speaker")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SpeakerService {

	@GET
	@Path("/ping")
	public String ping();

	@GET
	@Path("/")
	public List<Speaker> listSpeaker();
}
