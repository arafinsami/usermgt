package com.usermgt.utils;

import static java.util.Objects.isNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";

    public static String format(Date date, String format) {
        return isNull(date) ? null : new SimpleDateFormat(format).format(date);
    }
}
