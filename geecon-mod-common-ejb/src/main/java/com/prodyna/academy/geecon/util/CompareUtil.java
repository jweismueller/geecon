package com.prodyna.academy.geecon.util;

import java.lang.reflect.Field;

import com.prodyna.academy.geecon.auditing.AuditField;

public class CompareUtil {

	public static class ComapareResponse {

		private StringBuilder changes = new StringBuilder();

		public boolean hasChanges() {
			return changes.length() > 0;
		}

		public String getReport() {
			return changes.toString();
		}

		private void addChange(Field field, Object o1, Object o2) {
			changes.append("(");
			changes.append(field.getName());
			changes.append(": ");
			changes.append(String.valueOf(o2));
			changes.append(" -> ");
			changes.append(String.valueOf(o1));
			changes.append(") ");
		}
	}

	public static <T> ComapareResponse compare(T t1, T t2) throws IllegalArgumentException, IllegalAccessException {
		ComapareResponse out = new ComapareResponse();
		Field[] fields = t1.getClass().getDeclaredFields();
		if (fields != null) {
			for (Field field : fields) {
				if (field.isAnnotationPresent(AuditField.class)) {
					field.setAccessible(true);
					if ((field.get(t1)).equals(field.get(t2))) {
						// ok
					} else {
						out.addChange(field, field.get(t1), field.get(t2));
					}
					field.setAccessible(false);
				}
			}
		}
		return out;
	}
}
