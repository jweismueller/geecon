package com.prodyna.academy.geecon.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "conf_talk")
public class Talk extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 64)
	private String title;

	@Column(length = 1024)
	private String description;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestampFrom;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestampTill;

	@ManyToOne
	private Conference conference;

	@OneToMany
	@JoinColumn(name = "talk")
	private List<SpeakerAssignment> assignments = new ArrayList<SpeakerAssignment>();

}
