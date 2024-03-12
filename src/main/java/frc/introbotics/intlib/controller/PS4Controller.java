package frc.introbotics.intlib.controller;

import frc.introbotics.intlib.controller.component.IAxis;
import frc.introbotics.intlib.controller.component.Mappable;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PS4Controller extends Controller<PS4Controller.Button, PS4Controller.Axis> {

  @AllArgsConstructor
  @Getter
  public enum Button implements Mappable {
    Square(1),
    Cross(2),
    Circle(3),
    Triangle(4),
    L1(5),
    R1(6),
    L2(7),
    R2(8),
    Share(9),
    Options(10),
    L3(11),
    R3(12),
    PS(13),
    Touchpad(14);

    private final int id;
  }

  @AllArgsConstructor
  @Getter
  public enum Axis implements IAxis {
    LeftX(0),
    LeftY(1, true),
    RightX(2),
    RightY(5, true),
    L2(3),
    R2(4);

    private final int number;
    private final boolean inverted;

    Axis(int number) {
      this.number = number;
      this.inverted = false;
    }
  }

  public PS4Controller(int port) {
    super(port);
  }
}
