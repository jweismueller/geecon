package com.prodyna.academy.geecon.domain;

import org.junit.Assert;
import org.junit.Test;

public class AbstractEntityTest {

	@Test
	public void test() throws Exception {
		AbstractEntity entity = new AbstractEntity();
		entity.setId(234L);
		Assert.assertEquals(new Long(234L), entity.getId());
	}
}
