package com.prodyna.academy.geecon.service;

import javax.ejb.Stateless;

@Stateless
public class CommonServiceBean {

	public String hello(String s) {
		return "Hello " + s + "!";
	}

}
