package com.rambots4571.rampage.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import com.rambots4571.rampage.tools.PIDTuner;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;


public class MotorController implements Sendable {
  private final WPI_TalonFX motor;
  private final PIDTuner tuner;

  private int kPIDLoopIdx;
  private final int timeoutMs = 10;

  public MotorController(WPI_TalonFX motor) {
    this.motor = motor;
    this.tuner = new PIDTuner();
    this.tuner.setUpdater(this::updatePID);
    this.kPIDLoopIdx = 0;
  }

  public void setkPIDLoopIdx(int kPIDLoopIdx) {
    this.kPIDLoopIdx = kPIDLoopIdx;
  }

  public void setPIDF(double kP, double kI, double kD, double kF) {
    this.tuner.setPIDF(kP, kI, kD, kF);
    setMotorPIDF(kP, kI, kD, kF);
  }

  private void setMotorPIDF(double kP, double kI, double kD, double kF) {
    motor.config_kP(kPIDLoopIdx, kP, timeoutMs);
    motor.config_kI(kPIDLoopIdx, kI, timeoutMs);
    motor.config_kD(kPIDLoopIdx, kD, timeoutMs);
    motor.config_kF(kPIDLoopIdx, kF, timeoutMs);
  }

  /** This syncs up the tuner PIDF values with the motor. This should be run frequently */
  public void updatePID(PIDTuner tuner) {
    setMotorPIDF(tuner.getkP(), tuner.getkI(), tuner.getkD(), tuner.getkF());
  }

  public PIDTuner getTuner() {
    return this.tuner;
  }

  public double getRawSpeed() {
    return motor.getSelectedSensorVelocity(kPIDLoopIdx);
  }

  // This only works for low gear with a 15.32:1 gear reduction
  public double nativeUnitsToDistanceMeters(double sensorCounts, int encoderRes, double gearRatio, double wheelRadiusInch) {
    double motorRotations = (double) sensorCounts / encoderRes;
    double wheelRotations = motorRotations / gearRatio;
    double positionMeters =
        wheelRotations * (2 * Math.PI * Units.inchesToMeters(wheelRadiusInch));
    return positionMeters;
  }

  // This only works for low gear with a 15.32:1 gear reduction
  public double nativeUnitsToVelocity(double sensorCountsPer100ms, int encoderRes, double gearRatio, double wheelRadiusInch, int k100msPerSec) {
    double motorRotationsPer100ms = sensorCountsPer100ms / encoderRes;
    double motorRotationsPerSecond = motorRotationsPer100ms * k100msPerSec;
    double wheelRotationsPerSecond = motorRotationsPerSecond * gearRatio;
    double VelocityMetersPerSec =
        wheelRotationsPerSecond
            * (2 * Math.PI * Units.inchesToMeters(wheelRadiusInch));
    return VelocityMetersPerSec;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("MotorController");
    builder.addDoubleProperty("raw speed", this::getRawSpeed, null);
    builder.addDoubleProperty("rpm", this::getRPM, null);
    builder.addDoubleProperty("target RPM", () -> this.targetRPM, null);
    builder.addBooleanProperty("is at speed", this::isAtSpeed, null);
  }
}
