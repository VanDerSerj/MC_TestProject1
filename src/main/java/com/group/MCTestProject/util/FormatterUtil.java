package com.group.MCTestProject.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatterUtil {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    public static String getDateFormat (LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(localDateTime);

    }
}
