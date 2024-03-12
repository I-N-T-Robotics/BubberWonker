package frc.introbotics.intlib.controller;

import frc.introbotics.intlib.controller.component.IAxis;
import frc.introbotics.intlib.controller.component.Mappable;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class DriveStick extends Controller<DriveStick.Button, DriveStick.Axis> {

  @AllArgsConstructor
  @Getter
  public enum Button implements Mappable {
    button1(1),
    button2(2),
    button3(3),
    button4(4),
    button5(5),
    button6(6),
    button7(7),
    button8(8),
    button9(9),
    button10(10),
    button11(11),
    button12(12);

    private final int id;
  }

  @Getter
  @AllArgsConstructor
  public enum Axis implements IAxis {
    xAxis(0),
    yAxis(1, true),
    zAxis(2),
    Slider(3);

    private final int number;
    private final boolean inverted;

    Axis(int number) {
      this.number = number;
      this.inverted = false;
    }
  }

  public DriveStick(int port) {
    super(port);
  }
}
