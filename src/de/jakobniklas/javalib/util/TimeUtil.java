package de.jakobniklas.javalib.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class used for getting formatted dates
 *
 * @author Jakob-Niklas See
 * @see #format(String)
 * @see #getDate()
 * @see #getTime()
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
     */
    public static String format(String format)
    {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
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
}
