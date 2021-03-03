package com.rambots4571.rampage.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public final class Limelight {
    private static final NetworkTable table = NetworkTableInstance
            .getDefault().getTable("limelight");

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

    public enum LedMode {
        /**
         * Sets the LEDs to the value set for the current pipeline.
         */
        PipelineDefault(0),
        /**
         *  Forces the LEDs to turn off.
         */
        ForceOff(1),
        /**
         *  Forces the LEDs to blink.
         */
        ForceBlink(2),
        /**
         * Forces the LEDs to turn on.
         */
        ForceOn(3);
        public final int value;

        LedMode(int value) {
            this.value = value;
        }
    }

    public enum Stream {
        /**
         * Side-by-side streams if a webcam is attached to Limelight
         */
        Standard(0),
        /**
         * The secondary camera stream is placed in the lower-right
         * corner of the primary camera stream
         */
        PiP_Main(1),
        /**
         * The primary camera stream is placed in the lower-right
         * corner of the secondary camera stream
         */
        PiP_Secondary(2);

        public final int value;

        Stream(int value) {
            this.value = value;
        }
    }

    /**
     * Sets limelight’s LED state
     *
     * @param ledMode enum value
     */
    public static void setLedMode(LedMode ledMode) {
        table.getEntry("ledMode").setNumber(ledMode.value);
    }

    /**
     * Sets limelight’s operation mode
     *
     * @param camMode enum value
     */
    public static void setCamMode(CamMode camMode) {
        table.getEntry("camMode").setNumber(camMode.value);
    }

    /**
     * Sets limelight’s current pipeline
     *
     * @param number integer between 0 and 9
     */
    public static void setPipeline(int number) {
        if (0 <= number && number <= 9)
            table.getEntry("pipeline").setNumber(number);
    }

    /**
     * Get table values from the limelight. Check
     * {@link ReadValue} for all the values available.
     *
     * @param value enum value
     * @return a double check {@link ReadValue} for the ranges for each value
     */
    public static double get(ReadValue value) {
        return table.getEntry(value.name()).getDouble(0);
    }
}
