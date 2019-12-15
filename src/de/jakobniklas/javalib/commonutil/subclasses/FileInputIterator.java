package de.jakobniklas.javalib.commonutil.subclasses;

/**
 * Class to be implemented while iterating over a file in {@link de.jakobniklas.applicationlib.commonutil.FileUtil}
 *
 * @author Jakob-Niklas See
 * @see #action(String, int, IteratorOutputRegistry)
 */
public abstract class FileInputIterator
{
    /**
     * Action to be executed and implemented while iterating
     *
     * @param line                   The line which is iterated over
     * @param lineNumber             The numerical position of the line in the file
     * @param iteratorOutputRegistry A registry to store output for later use
     */
    public abstract void action(String line, int lineNumber, IteratorOutputRegistry iteratorOutputRegistry);
}
