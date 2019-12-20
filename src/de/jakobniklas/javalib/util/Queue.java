package de.jakobniklas.javalib.util;

import de.jakobniklas.javalib.util.actions.Action;

import java.util.Iterator;
import java.util.List;

/**
 * Class to {@link Iterator iterate} over a {@link List list} of {@link de.jakobniklas.javalib.util.actions.Action
 * actions}
 *
 * @author Jakob-Niklas
 * @see #list
 * @see #Queue(List)
 * @see #iterate()
 * @see #addNew(Action)
 */
public class Queue
{
    /**
     * ActionList containing the remaining {@link de.jakobniklas.javalib.util.actions.Action actions}
     *
     * @see #addNew(Action)
     */
    private List<Action> list;

    /**
     * Constructor to create a new queue object
     *
     * @param list Implemented {@link List list} of the type {@link de.jakobniklas.javalib.util.actions.Action
     *             Action}. Use "{@code new ArrayList<de.jakobniklas.util.actions.Action>()}" to create new queue list
     */
    public Queue(List<Action> list)
    {
        this.list = list;
    }

    /**
     * Iterates over any remaining {@link de.jakobniklas.javalib.util.actions.Action actions} in the {@link
     * #list actionList} and calls the {@link de.jakobniklas.javalib.util.actions.Action#perform()
     * perform()} method of each action
     */
    public void iterate()
    {
        Iterator<Action> iterator = this.list.iterator();

        while(iterator.hasNext())
        {
            int listID = list.indexOf(iterator.next());

            list.get(listID).perform();
            iterator.remove();
        }
    }

    /**
     * Adds new {@link de.jakobniklas.javalib.util.actions.Action action} to the {@link #list actionList}
     *
     * @param action Used implementation of an action object
     */
    public void addNew(Action action)
    {
        list.add(action);
    }
}
