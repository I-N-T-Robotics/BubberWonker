package com.rambots4571.rampage.controller.component;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class DPadButton extends Trigger {

  public DPadButton(GenericHID controller, Direction direction) {
    super(
        () -> {
          int dPadValue = controller.getPOV();

          return (dPadValue == direction.value)
              || (dPadValue == (direction.value + 45) % 360)
              || (dPadValue == (direction.value + 315) % 360);
        });
  }

  public static enum Direction {
    UP(0),
    RIGHT(90),
    DOWN(180),
    LEFT(270);

    int value;

    private Direction(int value) {
      this.value = value;
    }
  }
}
