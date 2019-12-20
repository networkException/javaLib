package de.jakobniklas.javalib.util;

import de.jakobniklas.javalib.util.subclasses.log.LogPattern;
import de.jakobniklas.javalib.util.subclasses.log.LogSection;
import de.jakobniklas.javalib.util.subclasses.log.Measurement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Logging class, used to write to {@link System#out}
 *
 * @author Jakob-Niklas See
 * //TODO: Add javadoc
 */
public class Log
{
    /**
     * Character which can be positioned with the tag '#logpointer' in the {@link #logPatterns}
     *
     * @see #getLogPointer()
     * @see #setLogPointer(Character)
     */
    private static Character logPointer = '>';

    /**
     * List of {@link LogPattern LogSections} which define the arrangement of values in the output of a 'Log.print()'
     * method call.
     * <br><br>
     * Tags:
     * <br> #date - date in the format specified in '{@link TimeUtil#getDate()
     * getDate()}'
     * <br> #time - time in the format specified in '{@link TimeUtil#getTime()
     * getTime()}'
     * <br> #milliseconds - number with type long as return value from '{@link System#currentTimeMillis()}'
     * <br> #prefix - prefix value specified in the '{@link #print(String, String)}' method with 'String prefix' in the
     * constructor
     * <br> #logPointer - separating character ({@link #logPointer})
     * <br> #message - input form the '{@link #print(String)}' method
     * <br> #class - returns the name of the class which called the print method
     * <br> #method - returns the name of the method which called the print method
     * <br> #line - returns the the line which called the print method
     * <br> #thread - thread from which the message is logged
     *
     * @see #print(String, String)
     * @see #print(String)
     * @see #getLogPatterns()
     * @see #setLogPatterns(List)
     */
    private static List<LogPattern> logPatterns = new ArrayList<>();

    private static Map<String, Measurement> measurements = new HashMap<>();

    /**
     * Sets the default configuration for {@link #logPatterns}
     */
    static
    {
        logPatterns.add(new LogPattern("[#date; #time; #milliseconds]"));
        logPatterns.add(new LogPattern(" #prefix"));
        logPatterns.add(new LogPattern(" #logPointer"));
        logPatterns.add(new LogPattern(" #message"));
        logPatterns.add(new LogPattern(" (#class"));
        logPatterns.add(new LogPattern(" #method"));
        logPatterns.add(new LogPattern(" #line"));
        logPatterns.add(new LogPattern(" / #file"));
        logPatterns.add(new LogPattern(" | #thread)"));
    }

    /**
     * Method which starts measuring the time from the call to the call of the Log.done() method
     *
     * @param prefix Prefix which can be used in the logging output, specified by the '#prefix' tag in {@link
     *               #logPatterns}
     * @param input  Message which should be used in the logging output, specified by the '#message' tag in {@link
     *               #logPatterns}
     * @param id     The id of the measure
     *
     * @see #print(String, String)
     * @see #done(String)
     * @see #measureTime(String, String)
     */
    public static void measureTime(String prefix, String input, String id)
    {
        print(prefix + " [m]", input);

        measurements.put(id, new Measurement(input, System.currentTimeMillis()));
    }

    /**
     * Method, which starts measuring the time from the call to the call of the Log.done() method
     *
     * @param input Message, which should be used in the logging output, specified by the '#message' tag in {@link
     *              #logPatterns}
     *
     * @see #print(String)
     * @see #done(String)
     * @see #measureTime(String, String, String)
     */
    public static void measureTime(String input, String id)
    {
        print("Log [" + id + "]", input);

        measurements.put(id, new Measurement(input, System.currentTimeMillis()));
    }

    /**
     * Method, which stops measuring the time, if Log.measureTime() was called before
     *
     * @param id The id of the measure to stop and log
     *
     * @see #measureTime(String, String, String)
     * @see #measureTime(String, String)
     */
    public static void done(String id)
    {
        if(measurements.containsKey(id))
        {
            String timeDifference = "";

            for(int i = 0; i < logPatterns.get(0).getLength() + logPatterns.get(1).getLength() + 1; i++)
            {
                timeDifference = timeDifference.concat(" ");
            }

            timeDifference = timeDifference + logPointer + " Done '" + measurements.get(id).getMessage() + "' in '" + (System.currentTimeMillis() - measurements.get(id).getTimestamp()) + "ms'";

            System.out.println(timeDifference);

            measurements.remove(id);
        }
    }

    public static Map<String, String> defaultSections()
    {
        StackTraceElement callerCallerElement = ClassUtil.firstNotClassElement("java.lang.Thread", "de.jakobniklas.javalib.util.ClassUtil", "de.jakobniklas.javalib.util.Log");

        Map<String, String> sections = new HashMap<>();
        sections.put("date", TimeUtil.getDate());
        sections.put("time", TimeUtil.getTime());
        sections.put("milliseconds", String.valueOf(System.currentTimeMillis()));
        sections.put("prefix", "Log");
        sections.put("message", "null");
        sections.put("class", callerCallerElement.getClassName());
        sections.put("logPointer", String.valueOf(logPointer));
        sections.put("method", callerCallerElement.getMethodName());
        sections.put("line", String.valueOf(callerCallerElement.getLineNumber()));
        sections.put("file", Objects.requireNonNull(callerCallerElement.getFileName()));
        sections.put("thread", Thread.currentThread().getName());

        return sections;
    }

    public static void print(String prefix, Object message)
    {
        print(prefix, String.valueOf(message));
    }

    public static void print(Object message)
    {
        print(String.valueOf(message));
    }

    public static void print(String prefix, String message, Object... args)
    {
        print(prefix, String.format(message, args));
    }

    public static void print(String message, Object... args)
    {
        print(String.format(message, args));
    }

    public static void print(String message)
    {
        Map<String, String> sections = defaultSections();
        sections.replace("message", message);

        print(sections);
    }

    public static void print(String prefix, String message)
    {
        Map<String, String> sections = defaultSections();
        sections.replace("prefix", prefix);
        sections.replace("message", message);

        print(sections);
    }

    public static void print(LogSection... logSections)
    {
        Map<String, String> sections = defaultSections();
        Arrays.asList(logSections).forEach((logSection) -> sections.replace(logSection.getKey(), logSection.getValue()));

        print(sections);
    }

    public static void print(Map<String, String> logSections)
    {
        System.out.println(FormatUtil.formatLog(logPatterns, logSections));
    }


/*
    public static void print(String input, Object... args)
    {
        print(String.format(input, args));
    }

    public static void print(String prefix, String input, Object... args)
    {
        print(prefix, String.format(input, args));
    }*/

    /**
     * @return {@link #logPointer}
     */
    public static char getLogPointer()
    {
        return logPointer;
    }

    /**
     * @param logPointer {@link #logPointer}
     */
    public static void setLogPointer(Character logPointer)
    {
        Log.logPointer = logPointer;
    }

    /**
     * @return {@link #logPatterns}
     */
    public static List<LogPattern> getLogPatterns()
    {
        return logPatterns;
    }

    /**
     * @param logPatterns {@link #logPatterns}
     */
    public static void setLogPatterns(List<LogPattern> logPatterns)
    {
        Log.logPatterns = logPatterns;
    }
}
