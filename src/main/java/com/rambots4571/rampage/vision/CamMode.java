package com.rambots4571.rampage.vision;

public enum CamMode {
    /**
     * Used for vision processing.
     */
    VisionCamera(0),
    /**
     *  Disables vision processing and used as a driver camera.
     */
    DriverCamera(1);

    public final int value;

    CamMode(int value) {
        this.value = value;
    }
}
