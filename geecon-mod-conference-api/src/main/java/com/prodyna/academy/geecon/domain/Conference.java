package com.prodyna.academy.geecon.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "conf_conference")
public class Conference extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 64)
	private String title;

	@Column(length = 1024)
	private String description;

	@Column
	@Temporal(TemporalType.DATE)
	private Calendar dateFrom;

	@Column
	@Temporal(TemporalType.DATE)
	private Calendar dateTill;

	@ManyToOne
	private Room room;

}
