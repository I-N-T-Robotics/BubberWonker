import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

public class PIDTuner implements Sendable {
  private double kP, kI, kD, kF;

  public PIDTuner(double kP, double kI, double kD, double kF) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.kF = kF;
  }

  public PIDTuner(double kP, double kI, double kD) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("kP", this::getkP, this::setkP);
    builder.addDoubleProperty("kI", this::getkI, this::setkI);
    builder.addDoubleProperty("kD", this::getkD, this::setkD);
    builder.addDoubleProperty("kF", this::getkF, this::setkF);
  }

  public double getkP() {
    return kP;
  }

  public void setkP(double kP) {
    this.kP = kP;
  }

  public double getkI() {
    return kI;
  }

  public void setkI(double kI) {
    this.kI = kI;
  }

  public double getkD() {
    return kD;
  }

  public void setkD(double kD) {
    this.kD = kD;
  }

  public double getkF() {
    return kF;
  }

  public void setkF(double kF) {
    this.kF = kF;
  }
}
