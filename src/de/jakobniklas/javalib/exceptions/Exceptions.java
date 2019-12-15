package de.jakobniklas.javalib.exceptions;

import de.jakobniklas.applicationlib.JakobniklasApplicationLibaryException;
import de.jakobniklas.applicationlib.commonutil.Log;

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
        Log.print("Exception", e.getMessage());

        e.printStackTrace();
    }

    /**
     * Handles events by calling implemented method {@link de.jakobniklas.applicationlib.exceptions.ExceptionHandleEvent#handle(Exception)
     * handle(Exception)}
     * <p>
     * Throws new {@link de.jakobniklas.applicationlib.exceptions.NoCustomExceptionsHandlerRegisteredException
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
     * de.jakobniklas.applicationlib.JakobniklasApplicationLibaryException ScrapeNodeException} and {@link
     * de.jakobniklas.applicationlib.commonutil.Log logging} with the parameter 'Exception'
     *
     * @param message message for the {@link de.jakobniklas.applicationlib.JakobniklasApplicationLibaryException
     *                ScrapeNodeException}
     */
    public static void handle(String message)
    {
        Exception e = new JakobniklasApplicationLibaryException(message);
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
