package de.jakobniklas.javalib.collection;

import de.jakobniklas.javalib.collection.subclasses.ListOrImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class wrapping any implementation of {@link List}
 * <p>
 * The map stores an {@link ArrayList} of values per key
 *
 * @param <K> The type of the key object
 * @param <V> The type of the Value object
 *
 * @author Jakob-Niklas See
 * @see #map
 * @see #ArrayMap()
 * @see #ArrayMap(Map)
 * @see #add(Object, Object)
 * @see #get(Object)
 * @see #get(Object, Integer)
 * @see #containsKey(Object)
 * @see #containsValue(Object, Object)
 * @see #indexOf(Object, Object)
 * @see #size(Object)
 * @see #stream(Object)
 * @see #remove(Object, Object)
 * @see #remove(Object, Integer)
 * @see #clear(Object)
 * @see #equals(Object)
 * @see #hashCode()
 * @see #get()
 */
public class ArrayMap<K, V>
{
    /**
     * The internal map
     */
    private Map<K, List<V>> map;

    /**
     * Creates a new ArrayMap, while using {@link HashMap} as the internal list's implementation
     */
    public ArrayMap()
    {
        this(new HashMap<>());
    }

    /**
     * Creates an ArrayMap with a given implementation of {@link Map}
     *
     * @param implementation The implementation to be using in {@link #map}
     */
    public ArrayMap(Map<K, List<V>> implementation)
    {
        map = implementation;
    }

    /**
     * Adds a given value to the list at a given key
     *
     * @param key   The given key
     * @param value The value to be added
     */
    public void add(K key, V value)
    {
        if(!map.containsKey(key)) { map.put(key, new ArrayList<>()); }

        map.get(key).add(value);
    }

    /**
     * Returns every entry at a given key
     *
     * @param key The key of the entry
     *
     * @return A list of values stored
     */
    public List<V> get(K key)
    {
        return map.get(key);
    }

    /**
     * Returns a value at a given index in a list of a given key
     *
     * @param key   The given key
     * @param index The index of the value
     *
     * @return The value at the given index
     */
    public V get(K key, Integer index)
    {
        return map.get(key).get(index);
    }

    /**
     * If the map contains a given key
     *
     * @param key The given key
     *
     * @return If the map contains the key
     */
    public Boolean containsKey(K key)
    {
        return map.containsKey(key);
    }

    /**
     * If a list at a given key contains a given value
     *
     * @param key   The given key
     * @param value The value in a list
     *
     * @return If the value is contained
     */
    public Boolean containsValue(K key, V value)
    {
        return map.get(key).contains(value);
    }

    /**
     * The index of a value in the list of a given key
     *
     * @param key   The given key
     * @param value The value
     *
     * @return The index of the value
     */
    public Integer indexOf(K key, V value)
    {
        return map.get(key).indexOf(value);
    }

    /**
     * The size of a list at a given key
     *
     * @param key The key of the list
     *
     * @return The size of the list
     */
    public Integer size(K key)
    {
        return map.get(key).size();
    }

    /**
     * A stream of values of a given key
     *
     * @param key The given keys
     *
     * @return The stream of values
     */
    public Stream<V> stream(K key)
    {
        return map.get(key).stream();
    }

    /**
     * Removes a given value from a list
     *
     * @param key   The key of the list
     * @param value The value to be removed
     */
    public void remove(K key, V value)
    {
        map.get(key).remove(value);
    }

    /**
     * Removes a given value from a list using its index
     *
     * @param key   The key of the list
     * @param index The index of the value to be removed
     */
    public void remove(K key, Integer index)
    {
        map.get(key).remove((int) index);
    }

    /**
     * Clears a list of a key
     *
     * @param key The list key
     */
    public void clear(K key)
    {
        map.get(key).clear();
    }

    /**
     * If two maps are equal
     *
     * @param o The other map
     *
     * @return If this map is equal to the other map
     */
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof ArrayMap)) return false;
        ArrayMap<?, ?> arrayMap = (ArrayMap<?, ?>) o;
        return Objects.equals(map, arrayMap.map);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(map);
    }

    /**
     * @return {@link #map}
     */
    public Map<K, List<V>> get()
    {
        return map;
    }
}
