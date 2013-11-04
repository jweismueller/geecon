package com.prodyna.academy.geecon.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conf_speakerassignment")
public class SpeakerAssignment extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Speaker speaker;

}
