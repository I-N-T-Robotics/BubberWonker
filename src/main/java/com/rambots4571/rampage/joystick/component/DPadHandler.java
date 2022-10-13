package com.rambots4571.rampage.joystick.component;

import com.rambots4571.rampage.joystick.component.DPadButton.Direction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import java.util.HashMap;

public class DPadHandler {
  private final HashMap<Direction, Button> buttons;
  private final Joystick joystick;

  public DPadHandler(Joystick joystick) {
    this.joystick = joystick;
    buttons = new HashMap<>();
  }

  public Button get(Direction direction) {
    if (buttons.containsKey(direction)) return buttons.get(direction);

    Button button = new DPadButton(joystick, direction);
    buttons.put(direction, button);

    return button;
  }
}
