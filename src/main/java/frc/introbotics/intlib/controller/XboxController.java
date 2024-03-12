package frc.introbotics.intlib.controller;

import frc.introbotics.intlib.controller.component.IAxis;
import frc.introbotics.intlib.controller.component.Mappable;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class XboxController extends Controller<XboxController.Button, XboxController.Axis> {
  @AllArgsConstructor
  @Getter
  public enum Button implements Mappable {
    A(1),
    B(2),
    X(3),
    Y(4),
    LeftBumper(5),
    RightBumper(6),
    Back(7),
    Start(8),
    LeftStick(9),
    RightStick(10);

    private final int id;
  }

  @AllArgsConstructor
  @Getter
  public enum Axis implements IAxis {
    LeftXAxis(0),
    LeftYAxis(1, true),
    LeftTrigger(2),
    RightTrigger(3),
    RightXAxis(4),
    RightYAxis(5, true);

    private final int number;
    private final boolean inverted;

    Axis(int number) {
      this.number = number;
      this.inverted = false;
    }
  }

  public XboxController(int port) {
    super(port);
  }
}
