package frc.introbotics.intlib.math;

import edu.wpi.first.math.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleSupplier;

public class Converter {

  public static enum Unit {
    RAW,
    METERS,
    INCHES,
    RADIANS,
    METERS_PER_SEC,
    RADIANS_PER_SEC,
    RPM;
  }

  public static interface Conversion {
    double apply(double value);
  }

  private DoubleSupplier baseValue;
  // Pair: 1 - converts raw to unit
  // Pair: 2 - converts unit to raw
  private Map<Unit, Pair<Conversion, Conversion>> converterFuncs;

  public Converter(DoubleSupplier baseValue) {
    this.baseValue = baseValue;
    converterFuncs = new HashMap<>(6);
  }

  public void addConversion(Unit unit, Conversion rawToUnit, Conversion unitToRaw) {
    converterFuncs.put(unit, new Pair<Conversion, Conversion>(rawToUnit, unitToRaw));
  }

  public double get(Unit unit) {
    if (converterFuncs.containsKey(unit))
      return converterFuncs.get(unit).getFirst().apply(baseValue.getAsDouble());
    return Integer.MAX_VALUE;
  }

  public double convertToBase(Unit unit, double value) {
    if (converterFuncs.containsKey(unit)) return converterFuncs.get(unit).getSecond().apply(value);
    return Integer.MAX_VALUE;
  }

  /**
   * @param positionCounts CANCoder Position Counts
   * @param gearRatio Gear Ratio between CANCoder and Mechanism
   * @return Degrees of Rotation of Mechanism
   */
  public static double CANcoderToDegrees(double positionCounts, double gearRatio) {
    return positionCounts * (360.0 / (gearRatio * 4096.0));
  }

  /**
   * @param degrees Degrees of rotation of Mechanism
   * @param gearRatio Gear Ratio between CANCoder and Mechanism
   * @return CANCoder Position Counts
   */
  public static double degreesToCANcoder(double degrees, double gearRatio) {
    return degrees / (360.0 / (gearRatio * 4096.0));
  }

  /**
   * @param counts Falcon Position Counts
   * @param gearRatio Gear Ratio between Falcon and Mechanism
   * @return Degrees of Rotation of Mechanism
   */
  public static double falconToDegrees(double positionCounts, double gearRatio) {
    return positionCounts * (360.0 / (gearRatio * 2048.0));
  }

  /**
   * @param degrees Degrees of rotation of Mechanism
   * @param gearRatio Gear Ratio between Falcon and Mechanism
   * @return Falcon Position Counts
   */
  public static double degreesToFalcon(double degrees, double gearRatio) {
    return degrees / (360.0 / (gearRatio * 2048.0));
  }

  /**
   * @param velocityCounts Falcon Velocity Counts
   * @param gearRatio Gear Ratio between Falcon and Mechanism (set to 1 for Falcon RPM)
   * @return RPM of Mechanism
   */
  public static double falconToRPM(double velocityCounts, double gearRatio) {
    double motorRPM = velocityCounts * (600.0 / 2048.0);
    double mechRPM = motorRPM / gearRatio;
    return mechRPM;
  }

  /**
   * @param RPM RPM of mechanism
   * @param gearRatio Gear Ratio between Falcon and Mechanism (set to 1 for Falcon RPM)
   * @return RPM of Mechanism
   */
  public static double RPMToFalcon(double RPM, double gearRatio) {
    double motorRPM = RPM * gearRatio;
    double sensorCounts = motorRPM * (2048.0 / 600.0);
    return sensorCounts;
  }

  /**
   * @param velocitycounts Falcon Velocity Counts
   * @param circumference Circumference of Wheel
   * @param gearRatio Gear Ratio between Falcon and Mechanism (set to 1 for Falcon MPS)
   * @return Falcon Velocity Counts
   */
  public static double falconToMPS(double velocitycounts, double circumference, double gearRatio) {
    double wheelRPM = falconToRPM(velocitycounts, gearRatio);
    double wheelMPS = (wheelRPM * circumference) / 60;
    return wheelMPS;
  }

  /**
   * @param velocity Velocity MPS
   * @param circumference Circumference of Wheel
   * @param gearRatio Gear Ratio between Falcon and Mechanism (set to 1 for Falcon MPS)
   * @return Falcon Velocity Counts
   */
  public static double MPSToFalcon(double velocity, double circumference, double gearRatio) {
    double wheelRPM = ((velocity * 60) / circumference);
    double wheelVelocity = RPMToFalcon(wheelRPM, gearRatio);
    return wheelVelocity;
  }

  /**
   * @param positionCounts Falcon Position Counts
   * @param circumference Circumference of Wheel
   * @param gearRatio Gear Ratio between Falcon and Wheel
   * @return Meters
   */
  public static double falconToMeters(
      double positionCounts, double circumference, double gearRatio) {
    return positionCounts * (circumference / (gearRatio * 2048.0));
  }

  /**
   * @param meters Meters
   * @param circumference Circumference of Wheel
   * @param gearRatio Gear Ratio between Falcon and Wheel
   * @return Falcon Position Counts
   */
  public static double MetersToFalcon(double meters, double circumference, double gearRatio) {
    return meters / (circumference / (gearRatio * 2048.0));
  }
}
