package de.jakobniklas.javalib.util.subclasses.log;

/**
 * Interface to be implemented in {@link de.jakobniklas.javalib.util.Log#registerLevel(LogLevel, LevelImplementation)
 * Log#registerLevel(LogLevel, LevelImplementation)}
 *
 * @author Jakob-Niklas See
 * @see #log(String)
 */
public interface LevelImplementation
{
    /**
     * Method to be implemented controlling the output of a logLevel (can be a lambda expression)
     *
     * @param string The input to be logged
     */
    void log(String string);
}
