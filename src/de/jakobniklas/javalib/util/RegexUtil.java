package de.jakobniklas.javalib.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for regular expression related functionality
 *
 * @author Jakob-Niklas See
 * @see #matches(String, String)
 * @see #allMatches(String, String)
 */
public class RegexUtil
{
    /**
     * Returns boolean if a String matches a regular expression
     *
     * @param pattern the regular expression to be matched with
     * @param input   The given input
     *
     * @return boolean if the input matches a regular expression
     * <p>
     * - wrapper for {@code input.matches(pattern);}
     */
    public static Boolean matches(String pattern, String input)
    {
        return input.matches(pattern);
    }

    /**
     * Returns an Iterable of all match groups in a string of a given pattern
     *
     * @param pattern The pattern which should be matched
     * @param input   The input which gets matched
     *
     * @return An Iterable of found groups
     */
    public static Iterable<MatchResult> allMatches(String pattern, String input)
    {
        return () -> new Iterator<>()
        {
            Matcher matcher = Pattern.compile(pattern).matcher(input);
            MatchResult pending;

            public boolean hasNext()
            {
                if(pending == null && matcher.find())
                {
                    pending = matcher.toMatchResult();
                }

                return pending != null;
            }

            public MatchResult next()
            {
                if(!hasNext()) { throw new NoSuchElementException(); }

                MatchResult next = pending;
                pending = null;
                return next;
            }

            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
}
