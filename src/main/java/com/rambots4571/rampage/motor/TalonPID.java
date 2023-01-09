package com.rambots4571.rampage.motor;

import com.ctre.phoenix.motorcontrol.can.BaseTalon;

import com.rambots4571.rampage.math.Converter;
import com.rambots4571.rampage.math.Converter.Unit;
import com.rambots4571.rampage.tools.PIDTuner;

import java.util.function.Function;

public class TalonPID {
  public static enum Mode {
    POSITION,
    VELOCITY
  }

  private final BaseTalon talon;
  private final PIDTuner tuner;
  private final Converter converter;

  private int kPIDLoopIdx = 0;
  private Mode mode = Mode.POSITION;

  private double rawTolerance;

  public TalonPID(BaseTalon talon) {
    this.talon = talon;
    this.tuner = new PIDTuner();
    this.tuner.setUpdater(this::updatePID);
    this.converter = new Converter(this::getRaw);
  }

  public void setkPIDLoopIdx(int kPIDLoopIdx) {
    this.kPIDLoopIdx = kPIDLoopIdx;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public double getRaw() {
    return mode == Mode.POSITION
        ? talon.getSelectedSensorPosition(kPIDLoopIdx)
        : talon.getSelectedSensorVelocity(kPIDLoopIdx);
  }

  public void addConversion(
      Unit unit, Function<Double, Double> rawToUnit, Function<Double, Double> unitToRaw) {
    converter.addConversion(unit, rawToUnit, unitToRaw);
  }

  public double getValue(Unit unit) {
    return converter.get(unit);
  }

  public double convertToRaw(Unit unit, double value) {
    return converter.convertToBase(unit, value);
  }

  public void setPID(double kP, double kI, double kD) {
    setMotorPIDF(kP, kI, kD, 0);
  }

  public void setPIDF(double kP, double kI, double kD, double kF) {
    tuner.setPIDF(kP, kI, kD, kF);
    setMotorPIDF(kP, kI, kD, kF);
  }

  private void setMotorPIDF(double kP, double kI, double kD, double kF) {
    talon.config_kP(kPIDLoopIdx, kP);
    talon.config_kI(kPIDLoopIdx, kI);
    talon.config_kD(kPIDLoopIdx, kD);
    talon.config_kF(kPIDLoopIdx, kF);
  }

  /** This syncs up the tuner PIDF values with the motor. This should be run frequently */
  private void updatePID(PIDTuner tuner) {
    setMotorPIDF(tuner.getkP(), tuner.getkI(), tuner.getkD(), tuner.getkF());
  }

  public void setTolerance(double rawTolerance) {
    this.rawTolerance = rawTolerance;
    talon.configAllowableClosedloopError(kPIDLoopIdx, rawTolerance);
  }

  public void setTolerance(Unit unit, double tolerance) {
    setTolerance(convertToRaw(unit, tolerance));
  }

  public boolean isAtSetPoint(double setpointRaw) {
    return Math.abs(setpointRaw - getRaw()) < rawTolerance;
  }

  public boolean isAtSetPoint(Unit unit, double setpoint) {
    return isAtSetPoint(convertToRaw(unit, setpoint));
  }

  public PIDTuner getTuner() {
    return this.tuner;
  }
}
