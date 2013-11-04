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

}
