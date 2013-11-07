package com.prodyna.academy.geecon.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.prodyna.academy.geecon.auditing.AuditField;

@Entity
@Table(name = "conf_talk")
public class Talk extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 64)
	private String title;

	@Column(length = 1024)
	private String description;

	@Column
	@Temporal(TemporalType.DATE)
	private Calendar dateOn;

	@AuditField
	@Column(length = 5)
	private String timeFrom;

	@AuditField
	@Column(length = 5)
	private String timeTill;

	@ManyToOne
	private Room room;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "talk")
	private List<SpeakerAssignment> assignments = new ArrayList<SpeakerAssignment>();

	@ManyToOne
	private Conference conference;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public List<SpeakerAssignment> getAssignments() {
		return assignments;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTill() {
		return timeTill;
	}

	public void setTimeTill(String timeTill) {
		this.timeTill = timeTill;
	}

	public Calendar getDateOn() {
		return dateOn;
	}

	public void setDateOn(Calendar dateOn) {
		this.dateOn = dateOn;
	}
}
