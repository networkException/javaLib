package de.jakobniklas.javalib.commonutil;

import de.jakobniklas.javalib.commonutil.subclasses.collection.ListOrImplementation;

import java.util.List;
import java.util.Map;

/**
 * Class for Collection Framework formatting and quick actions
 *
 * @author Jakob-Niklas See
 * @see #replaceOrPut(Map, Object, Object)
 */
public class CollectionUtil
{
    public static <K, V> void replaceOrPut(Map<K, V> map, K key, V value)
    {
        if(!map.containsKey(key)) {map.put(key, value);}
        else {map.replace(key, value);}
    }

    public static <T> void addOrImplementation(List<T> list, T value, ListOrImplementation implementation)
    {
        if(!list.contains(value)) {list.add(value);}
        else {implementation.or(list, value);}
    }
}
