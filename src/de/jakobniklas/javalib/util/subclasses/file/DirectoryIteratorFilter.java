package de.jakobniklas.javalib.util.subclasses.file;

import java.io.File;

/**
 * Functional interface to be implemented while iterating over a directory in {@link
 * de.jakobniklas.javalib.util.FileUtil} to filter if a file gets iterated over
 *
 * @author Jakob-Niklas See
 * @see #validate(File)
 */
@FunctionalInterface
public interface DirectoryIteratorFilter
{
    /**
     * Method to be implemented when iterating threw a directory (can be a lambda expression)
     *
     * @param file The current file which gets iterated over
     *
     * @return If the current file fits conditions to be iterated over
     */
    Boolean validate(File file);
}
