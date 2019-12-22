package de.jakobniklas.javalib.collection;

import de.jakobniklas.javalib.collection.subclasses.ListOrImplementation;

import java.util.List;
import java.util.Map;

/**
 * Class for Collection Framework formatting and quick actions
 *
 * @author Jakob-Niklas See
 * @see #replaceOrPut(Map, Object, Object)
 * @see #addOrImplementation(List, Object, ListOrImplementation)
 */
public class CollectionUtil
{
    /**
     * Replaces or puts a value with a given key to the list. If there is already a value with the same key in the map,
     * the value gets replaced
     *
     * @param map   The map to be operated on
     * @param key   The key of the entry
     * @param value The value to be put or replaced
     * @param <K>   The type of the key
     * @param <V>   The type of the value
     */
    public static <K, V> void replaceOrPut(Map<K, V> map, K key, V value)
    {
        if(!map.containsKey(key)) {map.put(key, value);}
        else {map.replace(key, value);}
    }

    /**
     * Adds a value to a list, if the value is not already in the list. Otherwise the {@link
     * ListOrImplementation#or(List, Object)} implementation gets called
     *
     * @param list           The list to be operated on
     * @param value          The value to be added
     * @param implementation An implementation of the or functionality (can be a lambda expression)
     * @param <T>            The type of the values in the list
     */
    public static <T> void addOrImplementation(List<T> list, T value, ListOrImplementation implementation)
    {
        if(!list.contains(value)) {list.add(value);}
        else {implementation.or((List<Object>) list, value);}
    }

    /**
     * Gets a value from a list, if the value is not in the list returns the default value.
     *
     * @param list         The list to be operated on
     * @param index        The index where the value should be
     * @param defaultValue The value which should be returned when no value is present
     * @param <T>          The type of the values in the list
     */
    public static <T> T getOrDefault(List<T> list, Integer index, T defaultValue)
    {
        T value = list.get(index);

        return value != null ? value : defaultValue;
    }
}
