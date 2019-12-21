package de.jakobniklas.javalib.exception;

/**
 * A class to be implemented which is a custom handler for exception. If registered can be called by {@link
 * de.jakobniklas.javalib.exception.Exceptions#customHandle(Exception)}
 *
 * @author Jakob-Niklas See
 * @see #handle(Exception)
 */
@FunctionalInterface
public interface ExceptionHandleEvent
{
    /**
     * The handler method to be implemented (can be a lambda expression)
     *
     * @param e the exception which should be handled
     */
    void handle(Exception e);
}
