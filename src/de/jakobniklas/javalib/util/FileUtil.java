package de.jakobniklas.javalib.util;

import de.jakobniklas.javalib.exception.Exceptions;
import de.jakobniklas.javalib.util.subclasses.file.DirectoryIterator;
import de.jakobniklas.javalib.util.subclasses.file.DirectoryIteratorFilter;
import de.jakobniklas.javalib.util.subclasses.file.FileInputIterator;
import de.jakobniklas.javalib.util.subclasses.file.FileOutputIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class to interact with the file system
 *
 * @author Jakob-Niklas See
 * @see #getTextContent(File) 
 * @see #getTextContent(String) 
 * @see #fileInputIterator(File, FileInputIterator) 
 * @see #fileInputIterator(String, FileInputIterator) 
 * @see #fileOutputIterator(File, List, Boolean) 
 * @see #fileOutputIterator(String, List, Boolean) 
 * @see #setTextContent(File, String) 
 * @see #setTextContent(String, String) 
 * @see #appendTextContent(File, String) 
 * @see #appendTextContent(String, String) 
 * @see #getAbsolutePath(String) 
 * @see #directoryIterator(File, DirectoryIterator) 
 * @see #directoryIterator(String, DirectoryIterator) 
 * @see #directoryIterator(File, DirectoryIteratorFilter, DirectoryIterator) 
 * @see #directoryIterator(String, DirectoryIteratorFilter, DirectoryIterator) 
 */
public class FileUtil
{
    /**
     * Returns the full content of a text file, separated by {@link System#lineSeparator()}
     *
     * @param file The file to read from
     *
     * @return The full content of the given text file
     *
     * @see #getTextContent(String)
     */
    public static String getTextContent(File file)
    {
        StringBuilder content = new StringBuilder();

        fileInputIterator(file, (line, lineNumber) -> content.append(line).append(System.lineSeparator()));

        return content.toString();
    }

    /**
     * Returns the full content of a text file, separated by {@link System#lineSeparator()}
     *
     * @param filepath The path and name of the file to read from
     *
     * @return The full content of the given text file
     *
     * @see #getTextContent(File)
     */
    public static String getTextContent(String filepath)
    {
        return getTextContent(new File(filepath));
    }

    /**
     * Iterates over a given file and executes {@link FileInputIterator#action(String, Integer)}
     *
     * @param file     The given file to be iterated over
     * @param iterator The implemented iterator to be called
     *
     * @see #fileInputIterator(String, FileInputIterator)
     */
    public static void fileInputIterator(File file, FileInputIterator iterator)
    {
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int count = 0;

            while((line = br.readLine()) != null)
            {
                iterator.action(line, count);

                count++;
            }

            fr.close();
            br.close();
        }
        catch(Exception e)
        {
            Exceptions.handle(e);
        }
    }

    /**
     * Iterates over a given file and executes {@link FileInputIterator#action(String, Integer)}
     *
     * @param filepath The given path and name of the file to be iterated over
     * @param iterator The implemented iterator to be called
     *
     * @see #fileInputIterator(File, FileInputIterator)
     */
    public static void fileInputIterator(String filepath, FileInputIterator iterator)
    {
        fileInputIterator(new File(filepath), iterator);
    }

    /**
     * Iterates over the given iterators and appends the return value to a given file
     *
     * @param file      The given file to append to
     * @param iterators The implemented list of {@link FileOutputIterator}
     * @param overwrite Toggles to overwrite the files content
     *
     * @see #fileOutputIterator(String, List, Boolean)
     */
    public static void fileOutputIterator(File file, List<FileOutputIterator> iterators, Boolean overwrite)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(file);
            AtomicInteger count = new AtomicInteger(0);

            if(overwrite)
            {
                fileWriter.write("");
            }

            iterators.forEach((iterator) ->
            {
                try
                {
                    fileWriter.append(iterator.action(count.getAndIncrement()));
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            });

            fileWriter.close();
        }
        catch(Exception e)
        {
            Exceptions.handle(e);
        }
    }

    /**
     * Iterates over the given iterators and appends the return value to a given file
     *
     * @param filepath  The given path and name of the file to append to
     * @param iterators The implemented list of {@link FileOutputIterator}
     * @param overwrite Toggles to overwrite the files content
     *
     * @see #fileOutputIterator(String, List, Boolean)
     */
    public static void fileOutputIterator(String filepath, List<FileOutputIterator> iterators, Boolean overwrite)
    {
        fileOutputIterator(new File(filepath), iterators, overwrite);
    }

    /**
     * Overwrites and appends the given content to a file
     *
     * @param file    The file to write to
     * @param content The content to write to the file
     *
     * @see #setTextContent(String, String)
     */
    public static void setTextContent(File file, String content)
    {
        fileOutputIterator(file, new ArrayList<>(Collections.singleton(lineNumber -> content)), true);
    }

    /**
     * Overwrites and appends the given content to a file
     *
     * @param filepath The path and name of the file to write to
     * @param content  The content to write to the file
     *
     * @see #setTextContent(File, String)
     */
    public static void setTextContent(String filepath, String content)
    {
        setTextContent(new File(filepath), content);
    }

    /**
     * Appends the given content to a file
     *
     * @param file    The file to write to
     * @param content The content to write to the file
     *
     * @see #setTextContent(File, String)
     */
    public static void appendTextContent(File file, String content)
    {
        fileOutputIterator(file, new ArrayList<>(Collections.singleton(lineNumber -> content)), false);
    }

    /**
     * Appends the given content to a file
     *
     * @param filepath The path and name of the file to write to
     * @param content  The content to write to the file
     *
     * @see #setTextContent(File, String)
     */
    public static void appendTextContent(String filepath, String content)
    {
        appendTextContent(new File(filepath), content);
    }

    /**
     * Returns the running directory + {@link File#separator} + the specified filename + {@link File#separator}
     *
     * @param fileName The specified fileName
     *
     * @return The path as a string
     */
    public static String getAbsolutePath(String fileName)
    {
        return System.getProperty("user.dir") + File.separator + fileName + File.separator;
    }

    /**
     * Iterates over a directory and running the iterator on every file
     *
     * @param directory The file of the directory to iterate over
     * @param iterator  The iterator implementation (can be a lambda expression)
     */
    public static void directoryIterator(File directory, DirectoryIterator iterator)
    {
        Arrays.asList(Objects.requireNonNull(directory.listFiles())).forEach(iterator::file);
    }

    /**
     * Iterates over a directory and running the iterator on every file
     *
     * @param directory The path to the directory to iterate over
     * @param iterator  The iterator implementation (can be a lambda expression)
     */
    public static void directoryIterator(String directory, DirectoryIterator iterator)
    {
        directoryIterator(new File(directory), iterator);
    }

    /**
     * Iterates over a directory and running the iterator on every file. The implemented filter deferments if the file
     * gets iterated over
     *
     * @param directory The file of the directory to iterate over
     * @param filter    The filer implementation (can be a lambda expression)
     * @param iterator  The iterator implementation (can be a lambda expression)
     */
    public static void directoryIterator(File directory, DirectoryIteratorFilter filter, DirectoryIterator iterator)
    {
        Arrays.stream(Objects.requireNonNull(directory.listFiles())).filter(filter::predicate).forEach(iterator::file);
    }

    /**
     * Iterates over a directory and running the iterator on every file. The implemented filter deferments if the file
     * gets iterated over
     *
     * @param directory The path to the directory to iterate over
     * @param filter    The filer implementation (can be a lambda expression)
     * @param iterator  The iterator implementation (can be a lambda expression)
     */
    public static void directoryIterator(String directory, DirectoryIteratorFilter filter, DirectoryIterator iterator)
    {
        directoryIterator(new File(directory), filter, iterator);
    }
}
