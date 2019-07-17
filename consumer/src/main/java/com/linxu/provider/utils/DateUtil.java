package com.linxu.provider.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linxu
 */
public class DateUtil {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * @param t1 1
     * @param t2 2
     * @return t1>=t2?1:0;
     */
    public static int compareTo(String t1, String t2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(t1);
        Date date2 = sdf.parse(t2);
        return date1.after(date2) ? 1 : 0;
    }

    /**
     *
     * @param t1 1
     * @param t2 2
     * @return t1<t2 = -1;
     * @throws ParseException
     */
    public static int compare(String t1, String t2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(t1);
        Date date2 = sdf.parse(t2);
        return date1.compareTo(date2);
    }
}
