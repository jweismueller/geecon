package com.prodyna.academy.geecon.util;

import java.util.Calendar;

public class CalendarUtil {

	public static Calendar getCalendar(int year, int monthStartingAtOne, int day) {
		Calendar out = getCalendarToday();
		out.set(year, monthStartingAtOne - 1, day);
		return out;
	}

	public static Calendar getCalendar(int year, int monthStartingAtOne, int day, int hour, int minutes) {
		Calendar out = getCalendarToday();
		out.set(year, monthStartingAtOne - 1, day);
		out.set(Calendar.HOUR_OF_DAY, hour);
		out.set(Calendar.MINUTE, minutes);
		return out;
	}

	public static Calendar getCalendarToday() {
		Calendar cal = Calendar.getInstance();
		cleanDay(cal);
		return cal;
	}

	public static void cleanDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

}
