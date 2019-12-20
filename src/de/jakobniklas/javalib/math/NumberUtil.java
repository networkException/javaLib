package de.jakobniklas.javalib.math;

/**
 * TODO: Add javadoc
 */
public class NumberUtil
{
    public static int randomNumber(int min, int max)
    {
        return (int) (Math.random() * ((max - min) + 1) + min);
    }
}
