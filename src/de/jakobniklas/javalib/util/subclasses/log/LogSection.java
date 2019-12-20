package de.jakobniklas.javalib.util.subclasses.log;

//TODO: Add javadoc
public class LogSection
{
    private String key;
    private String value;

    public LogSection(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}
