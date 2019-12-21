package de.jakobniklas.javalib.util.subclasses.file;

import java.io.File;

/**
 * Functional interface to be implemented while iterating over a directory in {@link
 * de.jakobniklas.javalib.util.FileUtil}
 *
 * @author Jakob-Niklas See
 * @see #file(File)
 */
@FunctionalInterface
public interface DirectoryIterator
{
    /**
     * Method to be implemented when iterating threw a directory (can be a lambda expression)
     *
     * @param file The current file which gets iterated over
     */
    void file(File file);
}
