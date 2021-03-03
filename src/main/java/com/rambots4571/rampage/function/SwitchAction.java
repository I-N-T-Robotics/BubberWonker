package com.rambots4571.rampage.function;

import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.function.Supplier;

/**
 * This is a subclass of the {@link Trigger} class. This allows us to run
 * commands or any actions when the state changes.
 *
 * @param <E> Any value you are watching to change to run an action.
 */
public class SwitchAction<E> extends Trigger {
    private final Supplier<E> stateSupplier;
    private E previousState, currentState;

    public SwitchAction(Supplier<E> stateSupplier) {
        this.stateSupplier = stateSupplier;
        previousState = null;
        currentState = stateSupplier.get();
    }

    /**
     * Returns whether or not the trigger is active.
     *
     * <p>This method will be called repeatedly a command is linked to the Trigger.
     *
     * @return whether or not the trigger condition is active.
     */
    @Override
    public boolean get() {
        previousState = currentState;
        currentState = stateSupplier.get();
        return currentState != previousState;
    }
}
