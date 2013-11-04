package com.prodyna.academy.geecon.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orga_location")
public class Location extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column
	private String title;

	@Column
	private String city;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "location")
	private List<Room> rooms = new ArrayList<Room>();

}
