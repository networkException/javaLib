package de.jakobniklas.javalib.commonutil.subclasses.exit;

import de.jakobniklas.javalib.commonutil.ExitUtil;

/**
 * Functional interface to be registered in {@link ExitHandleRegistry ExitRequestRegistry}
 *
 * @author Jakob-Niklas See
 * @see #handle() handle()
 */
@FunctionalInterface
public interface ExitHandleEvent
{
    /**
     * Implemented method gets called on running {@link ExitUtil#exit() exit()} or {@link ExitUtil#exit(Integer) exit(int
     * exitcode)}
     *
     * @return The name of the action taken
     */
    String handle();
}
