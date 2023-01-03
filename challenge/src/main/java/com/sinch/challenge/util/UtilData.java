package com.sinch.challenge.util;

import java.sql.Date;
import java.time.LocalDate;

public class UtilData {

	public static Date getSqlDateFromLocalDate(LocalDate localDate) {
		return Date.valueOf(localDate);
	}
}
