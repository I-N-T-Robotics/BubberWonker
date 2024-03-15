package frc.introbotics.intlib.util.function;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import java.util.function.DoubleBinaryOperator;
import lombok.Getter;
import lombok.Setter;

public class DoubleMonitor implements Sendable {
  private final DoubleBinaryOperator func;
  @Getter private double value;
  @Getter private double pollValue;
  @Getter @Setter private String valueName = "value";

  public DoubleMonitor(DoubleBinaryOperator func, double initialValue) {
    this.func = func;
    this.value = initialValue;
    this.pollValue = initialValue;
  }

  public DoubleMonitor(DoubleBinaryOperator func) {
    this.func = func;
    this.value = 0;
    this.pollValue = 0;
  }

  public void poll(double newValue) {
    pollValue = newValue;
    value = func.applyAsDouble(value, newValue);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("current", this::getPollValue, null);
    builder.addDoubleProperty(valueName, this::getValue, null);
  }

  public static DoubleMonitor maxMonitor(double initialValue) {
    var monitor = new DoubleMonitor(Math::max, initialValue);
    monitor.setValueName("Max Value");
    return monitor;
  }

  public static DoubleMonitor maxMonitor() {
    return maxMonitor(0);
  }

  public static DoubleMonitor minMonitor(double initialValue) {
    var monitor = new DoubleMonitor(Math::min, initialValue);
    monitor.setValueName("Min Value");
    return monitor;
  }

  public static DoubleMonitor minMonitor() {
    return minMonitor(0);
  }
}
