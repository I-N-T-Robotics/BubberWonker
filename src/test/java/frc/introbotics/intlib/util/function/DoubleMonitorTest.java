package frc.introbotics.intlib.util.function;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoubleMonitorTest {
  @Test
  void maxTest() {
    var list = new double[] {2, 3, 4, 3, 5, 6, 8, 4, 3};
    var monitor = DoubleMonitor.maxMonitor();
    for (double v : list) monitor.poll(v);
    assertEquals(8.0, monitor.getValue());
  }

  @Test
  void minTest() {
    var list = new double[] {2, 3, 4, 5, 6, 7, 4, 5, 6, 7, 4};
    var monitor = DoubleMonitor.minMonitor(5);
    for (double v : list) monitor.poll(v);
    assertEquals(2, monitor.getValue());
  }
}
