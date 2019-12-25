package de.jakobniklas.javalib.util;

import de.jakobniklas.javalib.exception.Exceptions;
import de.jakobniklas.javalib.util.subclasses.log.LogPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class for String formatting and detection
 *
 * @author Jakob-Niklas See
 * @see #isBoolean(String)
 * @see #isText(String)
 * @see #isNumeric(String)
 * @see #matchesRegex(String, String)
 * @see #streamToString(InputStream)
 * @see #padRight(String, Integer)
 * @see #padLeft(String, Integer)
 * @see #formatLog(List, Map)
 */
public class FormatUtil
{
    /**
     * Returns boolean if a String can be parsed to a number, exception not handled
     *
     * @param string given input
     *
     * @return boolean if the input can be parsed to a number (double)
     */
    public static boolean isNumeric(String string)
    {
        try
        {
            Double.parseDouble(string);

            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * Returns boolean if a String is not a number or a boolean
     *
     * @param string given input
     *
     * @return boolean if the input is not a number or a boolean
     */
    public static boolean isText(String string)
    {
        if(!isNumeric(string) && !isBoolean(string))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns boolean if a String is false or true
     *
     * @param string given input
     *
     * @return boolean if the input is false or true
     */
    public static boolean isBoolean(String string)
    {
        return string.equals("true") || string.equals("false");
    }

    /**
     * Returns boolean if a String matches a regular expression
     *
     * @param string given input
     * @param regex  the regular expression to be matched with
     *
     * @return boolean if the input matches a regular expression
     * <p>
     * - wrapper for {@code string.matches(regex);}
     */
    public static boolean matchesRegex(String string, String regex)
    {
        return string.matches(regex);
    }

    /**
     * Returns a string for a stream
     *
     * @param inputStream the given inputstream
     *
     * @return one string
     */
    public static String streamToString(InputStream inputStream)
    {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
        catch(IOException e)
        {
            Exceptions.handle(e);
        }

        return null;
    }

    /**
     * Expands string with space for a given amount of chars to the right
     *
     * @param input  The base string
     * @param length The amount of spaces
     *
     * @return The base string with the spaces appended
     */
    public static String padRight(String input, Integer length)
    {
        return String.format("%-" + length + "." + length + "s", input);
    }

    /**
     * Expands string with space for a given amount of chars to the left
     *
     * @param input  The base string
     * @param length The amount of spaces
     *
     * @return The base string with the spaces appended
     */
    public static String padLeft(String input, Integer length)
    {
        return String.format("%" + length + "." + length + "s", input);
    }

    /**
     * Formats a given list of sections to a given set of patterns. As used in {@link Log}, this formats a given map of
     * sections (The key representing the value in the pattern which should be replaced and the value the string which
     * should be inserted). The each element in the logPattern represents a section in the output. The logPattern should
     * therefor be stored globally, to allow formatted output according to the longest version of a section so far.
     * Technically does it not matter what keys are used, as a regex match finds the keys dynamically.
     * <p>
     * In the pattern, replaceable keys are defined as follows: {@code "text #key text"}
     *
     * @param logPatterns The pattern defining the outputs arrangement as well as incrementation
     * @param sections    The sections defining values of keys defined in the pattern
     *
     * @return A formatted String
     */
    public static String formatLog(List<LogPattern> logPatterns, Map<String, String> sections)
    {
        //Print the mapped output of each pattern.
        return logPatterns.stream().map((logPattern) ->
        {
            //The pattern with the section replaced
            AtomicReference<String> pattern = new AtomicReference<>(logPattern.getPattern() == null ? "null" : logPattern.getPattern());

            //Find a reference to the section in the pattern and replace the value
            RegexUtil.allMatches(Pattern.compile("#([a-zA-Z])+"), logPattern.getPattern()).forEach((foundMatch) ->
                pattern.set(pattern.get().replace(foundMatch.group(),
                    sections.get(foundMatch.group().substring(1)) == null ? "null" : sections.get(foundMatch.group().substring(1)))));

            /*
            Increase the length to get a formatted output

            [null; null; null] Test 10 null Hello World! (null null null / null | null)
            [null; null; null] Test    null Hello World! (null null null / null | null)
            */
            if(logPattern.getLength() < pattern.get().length())
            {
                logPattern.setLength(pattern.get().length());
            }

            //Return the string with added spaces
            return padRight(pattern.get(), logPattern.getLength());
        }).collect(Collectors.joining());
    }
}
