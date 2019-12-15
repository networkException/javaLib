package de.jakobniklas.javalib.commonutil.subclasses.collection;

import java.util.List;

/**
 * Functional interface to be implemented as an alternative action in {@link de.jakobniklas.javalib.commonutil.CollectionUtil}
 *
 * @author Jakob-Niklas See
 * @see #or(List, Object)
 */
@FunctionalInterface
public interface ListOrImplementation
{
    void or(List<Object> list, Object value);
}
