package de.jakobniklas.javalib.util;

import de.jakobniklas.javalib.collection.CollectionUtil;
import de.jakobniklas.javalib.util.subclasses.log.LevelImplementation;
import de.jakobniklas.javalib.util.subclasses.log.LogLevel;
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
 * @see #measureTime(String, String, String)
 * @see #measureTime(String, String)
 * @see #done(String)
 * @see #defaultSections()
 * @see #print(String, Object) print(prefix[string], message[object])
 * @see #print(LogLevel, String, Object) print(level[logLevel], prefix[string], message[object])
 * @see #print(Object) print(message[object])
 * @see #print(LogLevel, Object) print(level[logLevel], message[object])
 * @see #print(String, String, Object...) print(prefix[string], messsage[string, object...])
 * @see #print(LogLevel, String, String, Object...) print(level[logLevel], prefix[string], messsage[string, object...])
 * @see #print(String, Object...) print(message[string, object...])
 * @see #print(LogLevel, String, Object...) print(level[logLevel], message[string, object...])
 * @see #print(String) print(message[string])
 * @see #print(LogLevel, String) print(level[logLevel], message[string])
 * @see #print(String, String) print(prefix[string], message[string])
 * @see #print(LogLevel, String, String) print(level[logLevel], prefix[string], message[string])
 * @see #print(LogSection...) print(sections[logSection])
 * @see #print(LogLevel, LogSection...) print(level[logLevel], sections[logSection])
 * @see #print(Map) print(sections[string, string])
 * @see #print(LogLevel, Map) print(level[logLevel], sections[string, string])
 * @see #getLogPointer()
 * @see #setLogPointer(Character)
 * @see #getLogPatterns()
 * @see #setLogPatterns(List)
 * @see #getMeasurements()
 * @see #setMeasurements(Map)
 * @see #getLevels()
 * @see #setLevels(Map)
 * @see #registerLevel(LogLevel, LevelImplementation)
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
     * A list of patterns, portioning sections set in various print statements
     */
    private static List<LogPattern> logPatterns = new ArrayList<>();

    /**
     * A list of measurement id's and measurements, used to measure time between two points
     *
     * @see #measureTime(String, String)
     * @see #measureTime(String, String, String)
     * @see #done(String)
     */
    private static Map<String, Measurement> measurements = new HashMap<>();

    /**
     * A list of logLevels and their output implementation
     */
    private static Map<String, LevelImplementation> levels = new HashMap<>();

    /*
     * Sets the default configuration for the logPatterns and for the logLevels
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

        levels.put("trace", System.out::println);
        levels.put("debug", System.out::println);
        levels.put("info", System.out::println);
        levels.put("warn", System.err::println);
        levels.put("error", System.err::println);
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

    /**
     * <p><ul>
     * <li> date - The current date ({@link TimeUtil#getDate()})
     * <li> time - The current time ({@link TimeUtil#getTime()})
     * <li> milliseconds - The current unix timestamp ({@link System#currentTimeMillis()})
     * <li> prefix - The prefix of the log statement ({@code default: "Log"})
     * <li> logPointer - A character splitting prefix and message ({@code default: ">"})
     * <li> message - The message of the log statement ({@code default: "null"})
     * <li> class - The class calling any log method ({@link ClassUtil#firstNotClassElement(String...)})
     * <li> method - The method calling any log method ({@link ClassUtil#firstNotClassElement(String...)})
     * <li> line - The line in the method calling any log method ({@link ClassUtil#firstNotClassElement(String...)})
     * <li> file - The file of the class in the method calling any log method ({@link
     * ClassUtil#firstNotClassElement(String...)})
     * <li> file - The line of code in the method calling any log method ({@link ClassUtil#firstNotClassElement(String...)})
     * </ul><p>
     *
     * @return A default set of computed values for log output
     */
    public static Map<String, String> defaultSections()
    {
        StackTraceElement callerCallerElement = ClassUtil.firstNotClassElement("java.lang.Thread", "de.jakobniklas.javalib.util.ClassUtil", "de.jakobniklas.javalib.util.Log");

        Map<String, String> sections = new HashMap<>();
        sections.put("date", TimeUtil.getDate());
        sections.put("time", TimeUtil.getTime());
        sections.put("milliseconds", String.valueOf(System.currentTimeMillis()));
        sections.put("prefix", "Log");
        sections.put("logPointer", String.valueOf(logPointer));
        sections.put("message", "null");
        sections.put("class", callerCallerElement.getClassName());
        sections.put("method", callerCallerElement.getMethodName());
        sections.put("line", String.valueOf(callerCallerElement.getLineNumber()));
        sections.put("file", Objects.requireNonNull(callerCallerElement.getFileName()));
        sections.put("thread", Thread.currentThread().getName());

        return sections;
    }

    /**
     * Prints a given prefix and message to logLevel {@code info}
     *
     * @param prefix  The prefix to be logged
     * @param message The message to be logged
     *
     * @see #defaultSections()
     */
    public static void print(String prefix, Object message)
    {
        print(prefix, String.valueOf(message));
    }

    /**
     * Prints a given prefix and message to a given logLevel
     *
     * @param level   The log level of the output
     * @param prefix  The prefix to be logged
     * @param message The message to be logged
     *
     * @see #defaultSections()
     */
    public static void print(LogLevel level, String prefix, Object message)
    {
        print(level, prefix, String.valueOf(message));
    }

    /**
     * Prints a given message to logLevel {@code info}
     *
     * @param message The message to be logged
     *
     * @see #defaultSections()
     */
    public static void print(Object message)
    {
        print(String.valueOf(message));
    }

    /**
     * Prints a given message to a given logLevel
     *
     * @param level   The log level of the output
     * @param message The message to be logged
     *
     * @see #defaultSections()
     */
    public static void print(LogLevel level, Object message)
    {
        print(level, String.valueOf(message));
    }

    /**
     * Prints a given prefix and a given message to logLevel {@code info}
     * <p>
     * The message will be using args and formatted by {@link String#format(String, Object...)}
     *
     * @param prefix  The prefix to be logged
     * @param message The message to be logged
     * @param args    Formatting arguments for the message
     */
    public static void print(String prefix, String message, Object... args)
    {
        print(prefix, String.format(message, args));
    }

    /**
     * Prints a given prefix and a given message to a given logLevel
     * <p>
     * The message will be using args and formatted by {@link String#format(String, Object...)}
     *
     * @param level   The log level of the output
     * @param prefix  The prefix to be logged
     * @param message The message to be logged
     * @param args    Formatting arguments for the message
     */
    public static void print(LogLevel level, String prefix, String message, Object... args)
    {
        print(level, prefix, String.format(message, args));
    }

    /**
     * Prints a given message to logLevel {@code info}
     * <p>
     * The message will be using args and formatted by {@link String#format(String, Object...)}
     *
     * @param message The message to be logged
     * @param args    Formatting arguments for the message
     */
    public static void print(String message, Object... args)
    {
        print(String.format(message, args));
    }

    /**
     * Prints a given message to a given logLevel
     * <p>
     * The message will be using args and formatted by {@link String#format(String, Object...)}
     *
     * @param level   The log level of the output
     * @param message The message to be logged
     * @param args    Formatting arguments for the message
     */
    public static void print(LogLevel level, String message, Object... args)
    {
        print(level, String.format(message, args));
    }

    /**
     * Prints a given message to logLevel {@code info}
     *
     * @param message The message to be logged
     */
    public static void print(String message)
    {
        Map<String, String> sections = defaultSections();
        sections.replace("message", message);

        print(sections);
    }

    /**
     * Prints a given message to a given logLevel
     *
     * @param level   The log level of the output
     * @param message The message to be logged
     */
    public static void print(LogLevel level, String message)
    {
        Map<String, String> sections = defaultSections();
        sections.replace("message", message);

        print(level, sections);
    }

    /**
     * Prints a given prefix and message to logLevel {@code info}
     *
     * @param prefix  The prefix to be logged
     * @param message The message to be logged
     *
     * @see #defaultSections()
     */
    public static void print(String prefix, String message)
    {
        Map<String, String> sections = defaultSections();
        sections.replace("prefix", prefix);
        sections.replace("message", message);

        print(sections);
    }

    /**
     * Prints a given prefix and message to a given logLevel
     *
     * @param level   The log level of the output
     * @param prefix  The prefix to be logged
     * @param message The message to be logged
     *
     * @see #defaultSections()
     */
    public static void print(LogLevel level, String prefix, String message)
    {
        Map<String, String> sections = defaultSections();
        sections.replace("prefix", prefix);
        sections.replace("message", message);

        print(level, sections);
    }

    /**
     * Prints a list of given logSections to logLevel {@code info}, while overriding default sections
     *
     * @param logSections The logSections to be logged
     *
     * @see #defaultSections()
     */
    public static void print(LogSection... logSections)
    {
        Map<String, String> sections = defaultSections();
        Arrays.asList(logSections).forEach((logSection) -> sections.replace(logSection.getKey(), logSection.getValue()));

        print(sections);
    }

    /**
     * Prints a list of given logSections to a given logLevel, while overriding default sections
     *
     * @param level       The log level of the output
     * @param logSections The logSections to be logged
     *
     * @see #defaultSections()
     */
    public static void print(LogLevel level, LogSection... logSections)
    {
        Map<String, String> sections = defaultSections();
        Arrays.asList(logSections).forEach((logSection) -> sections.replace(logSection.getKey(), logSection.getValue()));

        print(level, sections);
    }

    /**
     * Prints a map of given logSections to logLevel {@code info}, while overriding default sections
     *
     * @param logSections The logSections to be logged
     *
     * @see #defaultSections()
     */
    public static void print(Map<String, String> logSections)
    {
        levels.get("info").log(FormatUtil.formatLog(logPatterns, logSections));
    }

    /**
     * Prints a map of given logSections to a given logLevel, while overriding default sections
     *
     * @param level       The log level of the output
     * @param logSections The logSections to be logged
     *
     * @see #defaultSections()
     */
    public static void print(LogLevel level, Map<String, String> logSections)
    {
        levels.get(level.getLevel()).log(FormatUtil.formatLog(logPatterns, logSections));
    }

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

    /**
     * @return {@link #measurements}
     */
    public static Map<String, Measurement> getMeasurements()
    {
        return measurements;
    }

    /**
     * @param measurements {@link #measurements}
     */
    public static void setMeasurements(Map<String, Measurement> measurements)
    {
        Log.measurements = measurements;
    }

    /**
     * @return {@link #levels}
     */
    public static Map<String, LevelImplementation> getLevels()
    {
        return levels;
    }

    /**
     * @param levels {@link #levels}
     */
    public static void setLevels(Map<String, LevelImplementation> levels)
    {
        Log.levels = levels;
    }

    /**
     * Overrides a registered logLevel or adds a new one
     *
     * @param level          The logLevel
     * @param implementation The implementation of the logLevel (can be a lambda expression)
     */
    public static void registerLevel(LogLevel level, LevelImplementation implementation)
    {
        CollectionUtil.replaceOrPut(levels, level.getLevel().toLowerCase(), implementation);
    }
}
