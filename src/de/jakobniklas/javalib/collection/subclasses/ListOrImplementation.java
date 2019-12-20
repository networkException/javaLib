package de.jakobniklas.javalib.collection.subclasses;

import de.jakobniklas.javalib.collection.CollectionUtil;

import java.util.List;

/**
 * Functional interface to be implemented as an alternative action in {@link CollectionUtil}
 *
 * @author Jakob-Niklas See
 * @see #or(List, Object)
 */
@FunctionalInterface
public interface ListOrImplementation
{
    /**
     * Implemented method gets called as an alternative action in {@link CollectionUtil} (can be a lambda expression)
     *
     * @param list  The list which is used
     * @param value The value which is used
     */
    void or(List<Object> list, Object value);
}
