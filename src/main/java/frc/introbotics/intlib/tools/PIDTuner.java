package frc.introbotics.intlib.tools;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

import java.util.function.Consumer;

public class PIDTuner implements Sendable {
  private double kP, kI, kD, kF = Math.PI;
  private Consumer<PIDTuner> updater;

  public PIDTuner(double kP, double kI, double kD, double kF) {
    setPIDF(kP, kI, kD, kF);
  }

  public PIDTuner(double kP, double kI, double kD) {
    setPID(kP, kI, kD);
  }

  public PIDTuner() {}

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("RobotPreferences");
    builder.addDoubleProperty("kP", this::getkP, this::setkP);
    builder.addDoubleProperty("kI", this::getkI, this::setkI);
    builder.addDoubleProperty("kD", this::getkD, this::setkD);
    // doesn't put this on the Sendable if it was never defined.
    if (kF != Math.PI) builder.addDoubleProperty("kF", this::getkF, this::setkF);
  }

  /**
   * Uses this to update an external PID system when the values get updated.
   *
   * @param updater
   */
  public void setUpdater(Consumer<PIDTuner> updater) {
    this.updater = updater;
  }

  public void setPIDF(double kP, double kI, double kD, double kF) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.kF = kF;
  }

  public void setPID(double kP, double kI, double kD) {
    setPIDF(kP, kI, kD, this.kF);
  }

  public double getkP() {
    return kP;
  }

  public void setkP(double kP) {
    this.kP = kP;
    updater.accept(this);
  }

  public double getkI() {
    return kI;
  }

  public void setkI(double kI) {
    this.kI = kI;
    updater.accept(this);
  }

  public double getkD() {
    return kD;
  }

  public void setkD(double kD) {
    this.kD = kD;
    updater.accept(this);
  }

  public double getkF() {
    return kF;
  }

  public void setkF(double kF) {
    this.kF = kF;
    updater.accept(this);
  }
}
