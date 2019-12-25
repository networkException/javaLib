package de.jakobniklas.javalib.exception;

import de.jakobniklas.javalib.JavaLibException;
import de.jakobniklas.javalib.util.Log;
import de.jakobniklas.javalib.util.subclasses.log.LogLevel;

/**
 * Class to handle any {@link Exception Exception} occurring
 *
 * @author Jakob-Niklas See
 * @see #handle(Exception)
 * @see #handle(String)
 * @see #customHandle(Exception)
 * @see #setCustomHandle(ExceptionHandleEvent)
 */
public class Exceptions
{
    private static ExceptionHandleEvent exceptionHandleEvent;

    /**
     * Handles exception occurring (accepting {@link Exception Exception} object) by printing the stacktrace to {@link
     * System#out}
     *
     * @param e {@link Exception Exception} object
     */
    public static void handle(Exception e)
    {
        Log.print(new LogLevel("error"), "Exception", e.getMessage());

        e.printStackTrace();
    }

    /**
     * Handles events by calling implemented method {@link de.jakobniklas.javalib.exception.ExceptionHandleEvent#handle(Exception)
     * handle(Exception)}
     * <p>
     * Throws new {@link de.jakobniklas.javalib.exception.NoCustomExceptionsHandlerRegisteredException
     * NoCustomExceptionsHandlerRegisteredException} if no custom handler was set before
     *
     * @param e exception to be handled
     *
     * @see #setCustomHandle(ExceptionHandleEvent)
     */
    public static void customHandle(Exception e)
    {
        if(exceptionHandleEvent != null)
        {
            exceptionHandleEvent.handle(e);
        }
        else
        {
            handle(new NoCustomExceptionsHandlerRegisteredException());
        }
    }

    /**
     * Handles exception occurring by accepting a message and throwing a new {@link
     * de.jakobniklas.javalib.JavaLibException JavaLibException} and {@link de.jakobniklas.javalib.util.Log logging}
     * with the parameter 'Exception'
     *
     * @param message message for the {@link de.jakobniklas.javalib.JavaLibException JavaLibException}
     */
    public static void handle(String message)
    {
        Exception e = new JavaLibException(message);
        handle(e);
    }

    /**
     * Sets a customHandleEvent for handling exceptions, called by {@link #customHandle(Exception)}
     *
     * @param event to be set
     *
     * @see #customHandle(Exception)
     */
    public static void setCustomHandle(ExceptionHandleEvent event)
    {
        exceptionHandleEvent = event;
    }
}
