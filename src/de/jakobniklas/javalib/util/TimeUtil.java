package de.jakobniklas.javalib.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class used for getting formatted dates
 *
 * @author Jakob-Niklas See
 * @see #format(String)
 * @see #format(String, Long) 
 * @see #getDate()
 * @see #getDate(Long)
 * @see #getTime()
 * @see #getTime(Long) 
 */
public class TimeUtil
{
    /**
     * Formats the current date and time according to the format parameter
     *
     * @param format Input for formatting a {@link SimpleDateFormat SimpleDateFormat}
     *
     * @return The formatted String according to the format parameter
     *
     * @see Calendar
     * @see #format(String, Long) 
     */
    public static String format(String format)
    {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }

    /**
     * Formats a given unix timestamp according to the format parameter
     *
     * @param format Input for formatting a {@link SimpleDateFormat SimpleDateFormat}
     * @param unix   The unix timestamp
     *
     * @return The formatted String according to the format parameter
     *
     * @see Calendar
     * @see #format(String) 
     */
    public static String format(String format, Long unix)
    {
        return new SimpleDateFormat(format).format(new Date(unix));
    }

    /**
     * Gets the current date in the 'dd.MM.yyyy' format ({@link SimpleDateFormat SimpleDateFormat})
     *
     * @return Formatted String
     *
     * @see #format(String)
     */
    public static String getDate()
    {
        return format("dd.MM.yyyy");
    }

    /**
     * Gets the date in the 'dd.MM.yyyy' format ({@link SimpleDateFormat SimpleDateFormat}) from a given unix timestamp
     *
     * @param unix The unix timestamp
     *
     * @return Formatted String
     *
     * @see #format(String, Long)
     */
    public static String getDate(Long unix)
    {
        return format("dd.MM.yyyy", unix);
    }

    /**
     * Gets the current time in the 'HH:mm:ss' format ({@link SimpleDateFormat SimpleDateFormat})
     *
     * @return Formatted String
     *
     * @see #format(String)
     */
    public static String getTime()
    {
        return format("HH:mm:ss");
    }

    /**
     * Gets the time in the 'HH:mm:ss' format ({@link SimpleDateFormat SimpleDateFormat}) from a given unix timestamp
     *
     * @param unix The unix timestamp
     *
     * @return Formatted String
     *
     * @see #format(String, Long)
     */
    public static String getTime(Long unix)
    {
        return format("HH:mm:ss", unix);
    }
}
