package com.prodyna.academy.geecon.rest.handler;

import javax.ejb.EJBException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.prodyna.academy.geecon.exception.GeeconValidationException;

@Provider
public class EJBExceptionHandler implements ExceptionMapper<EJBException> {

	@Override
	public Response toResponse(EJBException e) {
		if (e.getCause() instanceof GeeconValidationException) {
			return Response.status(400).entity(e.getCause().getMessage()).type(MediaType.APPLICATION_JSON).build();
		} else if (e.getCause() instanceof EntityNotFoundException) {
			return Response.status(404).type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.serverError().type(MediaType.APPLICATION_JSON).build();
		}
	}
}
