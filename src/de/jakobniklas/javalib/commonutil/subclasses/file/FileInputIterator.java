package de.jakobniklas.javalib.commonutil.subclasses.file;

/**
 * Functional interface to be implemented while iterating over a file in {@link de.jakobniklas.javalib.commonutil.FileUtil}
 *
 * @author Jakob-Niklas See
 * @see #action(String, Integer)
 */
@FunctionalInterface
public interface FileInputIterator
{
    /**
     * Action to be executed and implemented while iterating
     *
     * @param line                   The line which is iterated over
     * @param lineNumber             The numerical position of the line in the file
     */
    void action(String line, Integer lineNumber);
}
