package frc.introbotics.intlib.led;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class REVBlinkin extends Spark {

  public REVBlinkin(int channel) {
    super(channel);
  }

  public void setPattern(Pattern pattern) {
    setExpiration(pattern.getPulseWidth() * 10e-4);
    set(pattern.getSparkValue());
  }
}
