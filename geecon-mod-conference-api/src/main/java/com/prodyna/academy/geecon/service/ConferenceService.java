package com.prodyna.academy.geecon.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.prodyna.academy.geecon.domain.Conference;

@Path("/conference")
public interface ConferenceService {

	@GET
	@Path("/info")
	public String info();

	@GET
	@Path("/conferences")
	public List<Conference> list();

}
