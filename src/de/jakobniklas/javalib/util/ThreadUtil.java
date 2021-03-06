package de.jakobniklas.javalib.util;

import de.jakobniklas.javalib.exception.Exceptions;

/**
 * Class used to interact with the {@link Thread Thread} of the class which is calling this classes methods
 *
 * @author Jakob-Niklas See
 * @see #defaultSleep
 * @see #sleep()
 * @see #sleep(Integer)
 * @see #getDefaultSleep()
 * @see #setDefaultSleep(Integer)
 */
public class ThreadUtil
{
    /**
     * Default sleep duration in milliseconds. Used in {@link #sleep()}
     *
     * @see #getDefaultSleep()
     * @see #setDefaultSleep(Integer)
     */
    private static int defaultSleep = 10;

    /**
     * Pauses the {@link Thread Thread} of the calling class for an amount of milliseconds
     * <br> exceptions handled by {@link Exceptions Exceptions} class
     *
     * @param ms sleeping amount in milliseconds
     *
     * @see #sleep()
     */
    public static void sleep(Integer ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException e)
        {
            Exceptions.handle(e);
        }
    }

    /**
     * Pauses the {@link Thread Thread} of the calling class for the {@link #defaultSleep} amount of milliseconds
     *
     * @see #sleep(Integer)
     */
    public static void sleep()
    {
        sleep(defaultSleep);
    }

    /**
     * @return {@link #defaultSleep}
     */
    public static Integer getDefaultSleep()
    {
        return defaultSleep;
    }

    /**
     * @param defaultSleep {@link #defaultSleep}
     */
    public static void setDefaultSleep(Integer defaultSleep)
    {
        ThreadUtil.defaultSleep = defaultSleep;
    }

    /**
     * @return The name of the current Thread
     */
    public static String getThreadName()
    {
        return Thread.currentThread().getName();
    }

    /**
     * Sets the name of the currently running Thread
     *
     * @param name The name to be set
     */
    public static void setThreadName(String name)
    {
        Thread.currentThread().setName(name);
    }
}
