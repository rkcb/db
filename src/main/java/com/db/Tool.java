package com.db;

import java.sql.Timestamp;
import java.util.Calendar;

public class Tool {

    private static Calendar calendar;

    static {
        calendar = Calendar.getInstance();
    }

    public static Timestamp getTimestamp(){
        return new Timestamp(calendar.getTimeInMillis());
    }

}
