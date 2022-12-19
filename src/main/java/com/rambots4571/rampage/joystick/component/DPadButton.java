package com.rambots4571.rampage.joystick.component;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;

@SuppressWarnings("deprecation")
public class DPadButton extends Button {
  private final Joystick joystick;
  private final Direction direction;

  public DPadButton(Joystick joystick, Direction direction) {
    this.joystick = joystick;
    this.direction = direction;
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

  @Override
  public boolean getAsBoolean() {
    int dPadValue = joystick.getPOV();

    return (dPadValue == direction.value)
        || (dPadValue == (direction.value + 45) % 360)
        || (dPadValue == (direction.value + 315) % 360);
  }
}
