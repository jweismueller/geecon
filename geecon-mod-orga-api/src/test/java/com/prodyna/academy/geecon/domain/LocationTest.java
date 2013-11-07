package com.prodyna.academy.geecon.domain;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

	@Test
	public void test() throws Exception {
		Location entity = new Location();
		entity.setId(234L);
		Assert.assertEquals(Long.valueOf(234L), entity.getId());
	}
}
