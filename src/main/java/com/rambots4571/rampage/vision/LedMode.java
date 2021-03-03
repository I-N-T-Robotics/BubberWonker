package com.rambots4571.rampage.vision;

public enum LedMode {
    /**
     * Sets the LEDs to the value set for the current pipeline.
     */
    PipelineDefault(0),
    /**
     *  Forces the LEDs to turn off.
     */
    Off(1),
    /**
     *  Forces the LEDs to blink.
     */
    Blink(2),
    /**
     * Forces the LEDs to turn on.
     */
    On(3);
    public final int value;

    LedMode(int value) {
        this.value = value;
    }
}
