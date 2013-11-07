package com.prodyna.academy.geecon.service;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BuildCompleteArtifactTest {

	@Deployment(testable = false)
	public static Archive<?> createTestArchive() {
		File[] libs = Maven
				.resolver()
				.loadPomFromFile("pom.xml")
				.resolve("com.prodyna.academy.geecon:geecon-mod-common-api",
						"com.prodyna.academy.geecon:geecon-mod-common-ejb",
						"com.prodyna.academy.geecon:geecon-mod-conference-api",
						"com.prodyna.academy.geecon:geecon-mod-conference-ejb",
						"com.prodyna.academy.geecon:geecon-mod-orga-api",
						"com.prodyna.academy.geecon:geecon-mod-orga-ejb").withoutTransitivity().asFile();
		//
		WebArchive archive = ShrinkWrap.create(WebArchive.class).addPackages(true, "com/prodyna/pac/conference")
				.deletePackages(true, "com/prodyna/pac/conference/rest/test")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("META-INF/test-orm.xml", "META-INF/orm.xml")
				.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml").addAsLibraries(libs);
		System.out.println(archive.toString(true));
		return archive;
	}

	@ArquillianResource
	private URL contextPath;

	@Test
	@RunAsClient
	public void testThis() throws Exception {
	}
}
