/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 16, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.context.i18n.SimpleLocaleContext;

public class FormatDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println(format(date));

		String sDate1 = "31/12/1998 12:20";
		System.out.println(toDate(sDate1));
//		LocalDate localDate=LocalDate.now();
		String sDate2 = "31-12-1998";
		LocalDate localDate = toLocalDate(sDate2);
		System.out.println(localDate);
		String sDate3 = "19:20";
		LocalTime localTime = toLocalTime(sDate3);
		System.out.println(localTime);

	}

	public static String format(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formatter.format(date);
	}

	public static Date toDate(String s) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static LocalDate toLocalDate(String s) {
		LocalDate date = null;

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		date = LocalDate.parse(s, format);

		return date;
	}

	public static LocalTime toLocalTime(String s) {
		

		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime date = LocalTime.parse(s, format);

		return date;
	}
}
