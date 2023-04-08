package ifnmg.aps.atividade1.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateTimeUtils {
	
	private static DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	public static LocalDate dateFromString(String dateString) {
		return dateString != null ? LocalDate.parse(dateString, localDateFormatter) : null;
	}
	
	public static LocalTime timeFromString(String timeString) {
		return timeString != null ? LocalTime.parse(timeString, localTimeFormatter) : null;
	}
	
	public static String dateToString(LocalDate date) {
		return date != null ? localDateFormatter.format(date) : null;
	}
	
	public static String timeToString(LocalTime time) {
		return time != null ? localTimeFormatter.format(time) : null;
	}
	
	public static String mesToString(Month mes) {
		return mes.getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pt-BR"));
	}
}
