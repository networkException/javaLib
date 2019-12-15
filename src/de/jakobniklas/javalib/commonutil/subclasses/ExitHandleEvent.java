package de.jakobniklas.javalib.commonutil.subclasses;

import de.jakobniklas.applicationlib.commonutil.ExitUtil;

/**
 * Class to be registered in {@link ExitHandleRegistry ExitRequestRegistry}
 *
 * @author Jakob-Niklas See
 * @see #handle() handle()
 */
public abstract class ExitHandleEvent
{
    /**
     * Implemented method gets called on running {@link ExitUtil#exit() exit()} or {@link ExitUtil#exit(int) exit(int
     * exitcode)}
     */
    public abstract void handle();

    /**
     * To be implemented method which returns the name of the given action
     *
     * @return name of the action which the event is performing
     */
    public abstract String getName();
}
