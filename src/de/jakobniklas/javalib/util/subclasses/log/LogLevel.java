package de.jakobniklas.javalib.util.subclasses.log;

public class LogLevel
{
    private String level;

    public LogLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level.toLowerCase();
    }
}
