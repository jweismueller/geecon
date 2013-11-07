package com.prodyna.academy.geecon.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.academy.geecon.domain.Conference;
import com.prodyna.academy.geecon.domain.SpeakerAssignment;
import com.prodyna.academy.geecon.domain.Talk;
import com.prodyna.academy.geecon.exception.GeeconValidationException;
import com.prodyna.academy.geecon.util.CalendarUtil;

public class ConferenceValidator {

	@Inject
	Logger log;

	@Inject
	private EntityManager em;

	public void validate(Conference c, Talk talk) {
		boolean r = CalendarUtil.isCalendarInRangeIncl(c.getDateFrom(), c.getDateTill(), talk.getDateOn());
		if (!r) {
			log.error("Talk date is out of range.");
			throw new GeeconValidationException("Talk date is out of range.");
		}
		Map<Long, Talk> map = getAllTalks(c);
		if (talk.getId() == null) {
			map.put(0L, talk);
		} else {
			map.put(talk.getId(), talk);
		}
		Map<Long, List<TalkModel>> speakerMap = new HashMap<>();
		Map<Long, List<TalkModel>> roomMap = new HashMap<>();
		for (Talk t : map.values()) {
			if (t.getRoom() != null) {
				Long key = t.getRoom().getId();
				if (!roomMap.containsKey(key)) {
					roomMap.put(key, new ArrayList<TalkModel>());
				}
				roomMap.get(key).add(new TalkModel(t));
			}
			for (SpeakerAssignment assignment : t.getAssignments()) {
				Long key = assignment.getSpeaker().getId();
				if (!speakerMap.containsKey(key)) {
					speakerMap.put(key, new ArrayList<TalkModel>());
				}
				speakerMap.get(key).add(new TalkModel(t));
			}
		}
		compareMaps(speakerMap, "speaker");
		compareMaps(roomMap, "room");
	}

	private void compareMaps(Map<Long, List<TalkModel>> map, String context) {
		Set<Entry<Long, List<TalkModel>>> entrySet = map.entrySet();
		for (Entry<Long, List<TalkModel>> entry : entrySet) {
			for (TalkModel t1 : entry.getValue()) {
				for (TalkModel t2 : entry.getValue()) {
					if (t1 == t2)
						continue;
					boolean ra = CalendarUtil.isCalendarInRangeIncl(t1.from, t1.till, t2.from);
					if (ra) {
						log.error("Overlap of talks in " + context + " [" + entry.getKey() + "].");
						throw new GeeconValidationException("Overlap of talks in " + context + "[" + entry.getKey()
								+ "].");
					}
				}
			}
		}
	}

	private Map<Long, Talk> getAllTalks(Conference c) {
		TypedQuery<Talk> query = em.createNamedQuery("listTalks", Talk.class);
		query.setParameter("cId", c.getId());
		Map<Long, Talk> out = new HashMap<>();
		for (Talk t : query.getResultList()) {
			out.put(t.getId(), t);
		}
		return out;
	}

	static class TalkModel {

		Calendar from;

		Calendar till;

		public TalkModel(Talk t) {
			from = parse(t.getDateOn(), t.getTimeFrom());
			till = parse(t.getDateOn(), t.getTimeTill());
		}
	}

	private static Calendar parse(Calendar date, String time) {
		SimpleDateFormat ISO = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String complete = ISO.format(date.getTime()) + " " + time;
		Date dateTime;
		try {
			dateTime = DATETIME.parse(complete);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Calendar out = Calendar.getInstance();
		out.setTime(dateTime);
		out.set(Calendar.SECOND, 0);
		out.set(Calendar.MILLISECOND, 0);
		return out;
	}
}
