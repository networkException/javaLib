package de.jakobniklas.javalib.commonutil.actions;

/**
 * Functional interface to be implemented by any in a {@link de.jakobniklas.javalib.commonutil.Queue Queue} performable
 * action
 *
 * @author Jakob-Niklas See
 * @see #perform()
 */
@FunctionalInterface
public interface Action
{
    /**
     * Abstract method which is being called by the {@link de.jakobniklas.javalib.commonutil.Queue#iterate()
     * queue-iterator} method
     */
    void perform();
}
