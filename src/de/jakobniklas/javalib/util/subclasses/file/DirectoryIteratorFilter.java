package de.jakobniklas.javalib.util.subclasses.file;

import java.io.File;

/**
 * Functional interface to be implemented while iterating over a directory in {@link
 * de.jakobniklas.javalib.util.FileUtil} to filter if a file gets iterated over
 *
 * @author Jakob-Niklas See
 * @see #predicate(File)
 */
@FunctionalInterface
public interface DirectoryIteratorFilter
{
    //TODO: Add javadoc
    Boolean predicate(File file);
}
