package de.jakobniklas.javalib.util.subclasses.file;

/**
 * Functional interface to be implemented while iterating over a file in {@link de.jakobniklas.javalib.util.FileUtil}
 *
 * @author Jakob-Niklas See
 * @see #action(Integer)
 */
@FunctionalInterface
public interface FileOutputIterator
{
    /**
     * Action to be executed while iterating over a file and outputting to it
     *
     * @param lineNumber The numerical position of the current line
     *
     * @return The output which gets appended to the line
     */
    String action(Integer lineNumber);
}
