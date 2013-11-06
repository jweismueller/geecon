package com.prodyna.academy.geecon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orga_speaker")
public class Speaker extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 64)
	private String name;

	@Column(length = 1024)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
