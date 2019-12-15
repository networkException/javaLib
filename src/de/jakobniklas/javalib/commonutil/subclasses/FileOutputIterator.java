package de.jakobniklas.javalib.commonutil.subclasses;

/**
 * Class to be implemented while iterating over a file in {@link de.jakobniklas.applicationlib.commonutil.FileUtil}
 *
 * @author Jakob-Niklas See
 * @see #action(int)
 */
public abstract class FileOutputIterator
{
    /**
     * Action to be executed while iterating over a file and outputting to it
     *
     * @param lineNumber The numerical position of the current line
     *
     * @return The output which gets appended to the line
     */
    public abstract String action(int lineNumber);
}
