package frc.introbotics.intlib.controller.component;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.HashMap;

public class DPadHandler {
  private final HashMap<DPadButton.Direction, Trigger> buttons;
  private final GenericHID controller;

  public DPadHandler(GenericHID controller) {
    this.controller = controller;
    buttons = new HashMap<>();
  }

  public Trigger get(DPadButton.Direction direction) {
    if (buttons.containsKey(direction)) return buttons.get(direction);

    Trigger button = new DPadButton(controller, direction);
    buttons.put(direction, button);

    return button;
  }
}
