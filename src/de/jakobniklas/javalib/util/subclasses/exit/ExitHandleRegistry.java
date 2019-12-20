package de.jakobniklas.javalib.util.subclasses.exit;

import de.jakobniklas.javalib.collection.CollectionUtil;
import de.jakobniklas.javalib.exception.Exceptions;
import de.jakobniklas.javalib.exception.ExitHandleEventAlreadyRegisteredException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for registering exit handler objects {@link ExitHandleEvent ExitHandleEvent}
 *
 * @author Jakob-Niklas See
 * @see #registeredEvents
 * @see #register(ExitHandleEvent)
 * @see #ExitHandleRegistry()
 * @see #handleRegisteredEvents()
 */
public class ExitHandleRegistry
{
    /**
     * List of type {@link ExitHandleEvent ExitHandleEvent} which stores registered events
     */
    List<ExitHandleEvent> registeredEvents;

    /**
     * Constructor of the class, initializes {@link #registeredEvents}
     */
    public ExitHandleRegistry()
    {
        registeredEvents = new ArrayList<>();
    }

    /**
     * goes threw each registered event and executes the implemented method, gets called by {@link
     * de.jakobniklas.javalib.util.ExitUtil ExitUtil}
     */
    public void handleRegisteredEvents()
    {
        registeredEvents.forEach(ExitHandleEvent::handle);
    }

    /**
     * registers a {@link ExitHandleEvent ExitHandleEvent} to be called by {@link #handleRegisteredEvents()}
     * <p>
     * Throws a new {@link de.jakobniklas.javalib.exception.ExitHandleEventAlreadyRegisteredException
     * ExitHandleEventAlreadyRegisteredException} (handled by {@link de.jakobniklas.javalib.exception.Exceptions
     * Exceptions})
     *
     * @param event the implemented event to be registered
     */
    public void register(ExitHandleEvent event)
    {
        CollectionUtil.addOrImplementation(registeredEvents, event, (list, value) ->
            Exceptions.handle(new ExitHandleEventAlreadyRegisteredException(event.getClass().getName())));
    }
}
