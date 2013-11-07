package com.prodyna.academy.geecon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orga_room")
public class Room extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 64)
	private String title;

	@Column
	private Integer capacity;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}
