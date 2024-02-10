package frc.introbotics.intlib.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public final class Limelight {
  private static Limelight instance;
  private final NetworkTable table;

  private Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public static Limelight getInstance() {
    if (instance == null) instance = new Limelight();
    return instance;
  }

  public enum CamMode {
    /** Used for vision processing. */
    VisionCamera(0),
    /** Disables vision processing and used as a driver camera. */
    DriverCamera(1);

    public final int value;

    CamMode(int value) {
      this.value = value;
    }
  }

  public enum LedMode {
    /** Sets the LEDs to the value set for the current pipeline. */
    PipelineDefault(0),
    /** Forces the LEDs to turn off. */
    ForceOff(1),
    /** Forces the LEDs to blink. */
    ForceBlink(2),
    /** Forces the LEDs to turn on. */
    ForceOn(3);

    public final int value;

    LedMode(int value) {
      this.value = value;
    }
  }

  public enum Stream {
    /** Side-by-side streams if a webcam is attached to Limelight */
    Standard(0),
    /**
     * The secondary camera stream is placed in the lower-right corner of the primary camera stream
     */
    PiP_Main(1),
    /**
     * The primary camera stream is placed in the lower-right corner of the secondary camera stream
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
  public void setLedMode(LedMode ledMode) {
    table.getEntry("ledMode").setNumber(ledMode.value);
  }

  /**
   * Sets limelight’s operation mode
   *
   * @param camMode enum value
   */
  public void setCamMode(CamMode camMode) {
    table.getEntry("camMode").setNumber(camMode.value);
  }

  /**
   * Sets limelight’s current pipeline
   *
   * @param number integer between 0 and 9
   */
  public void setPipeline(int number) {
    if (0 <= number && number <= 9) table.getEntry("pipeline").setNumber(number);
  }

  public NetworkTableEntry getEntry(ReadValue value) {
    return table.getEntry(value.name());
  }

  public NetworkTableEntry getEntry(String s) {
    return table.getEntry(s);
  }

  public double getValue(ReadValue value, double defaultValue) {
    return table.getEntry(value.name()).getDouble(defaultValue);
  }

  public double getValue(ReadValue value) {
    return getValue(value, Integer.MAX_VALUE);
  }
}
