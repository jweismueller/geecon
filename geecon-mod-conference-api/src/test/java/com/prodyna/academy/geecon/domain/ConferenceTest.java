package com.prodyna.academy.geecon.domain;

import org.junit.Assert;
import org.junit.Test;

public class ConferenceTest {

	@Test
	public void test() throws Exception {
		Conference entity = new Conference();
		entity.setId(234L);
		Assert.assertEquals(234L, entity.getId());
	}

}
