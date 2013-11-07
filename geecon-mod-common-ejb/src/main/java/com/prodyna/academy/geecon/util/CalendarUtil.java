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

	public static boolean isCalendarInRangeIncl(Calendar start, Calendar end, Calendar cal) {
		return isCalendarInRange(start, true, end, true, cal);
	}

	public static boolean isCalendarInRangeExcl(Calendar start, Calendar end, Calendar cal) {
		return isCalendarInRange(start, false, end, false, cal);
	}

	public static boolean isCalendarInRange(Calendar start, boolean startIncl, Calendar end, boolean endIncl,
			Calendar cal) {
		if (cal == null) {
			return false;
		} else if (cal.before(start)) {
			return false;
		} else if (cal.after(end)) {
			return false;
		} else if (cal.equals(start)) {
			return startIncl;
		} else if (cal.equals(end)) {
			return endIncl;
		} else {
			return true;
		}
	}
}
