package de.jakobniklas.javalib.util.actions;

/**
 * Functional interface to be implemented by any in a {@link de.jakobniklas.javalib.util.Queue Queue} performable
 * action
 *
 * @author Jakob-Niklas See
 * @see #perform()
 */
@FunctionalInterface
public interface Action
{
    /**
     * Abstract method which is being called by the {@link de.jakobniklas.javalib.util.Queue#iterate()
     * queue-iterator} method
     */
    void perform();
}
