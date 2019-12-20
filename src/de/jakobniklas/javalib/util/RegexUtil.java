package de.jakobniklas.javalib.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil
{
    public static Iterable<MatchResult> allMatches(Pattern pattern, String input)
    {
        return () -> new Iterator<>()
        {
            Matcher matcher = pattern.matcher(input);
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
