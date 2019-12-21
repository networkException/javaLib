package de.jakobniklas.javalib.util.subclasses.log;

/**
 * Class storing the level of log outputs. This class wraps {@link String}, so that in the various print statements its
 * distinct
 *
 * @author Jakob-Niklas See
 * @see #level
 * @see #LogLevel(String)
 * @see #getLevel()
 */
public class LogLevel
{
    /**
     * The value of the logLevel
     */
    private String level;

    /**
     * Creates a new logLevel with a given value
     *
     * @param level The given value of the level
     */
    public LogLevel(String level)
    {
        this.level = level;
    }

    /**
     * @return {@link #level}
     */
    public String getLevel()
    {
        return level.toLowerCase();
    }
}
