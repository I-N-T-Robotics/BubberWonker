package com.rambots4571.rampage.joystick.component;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.HashMap;

public class Buttons<B extends Enum<B> & Mappable> {
  private final HashMap<B, Trigger> buttonsMap;
  private final Joystick joystick;

  public Buttons(Joystick joystick) {
    buttonsMap = new HashMap<>();
    this.joystick = joystick;
  }

  public Trigger get(B button) {
    if (buttonsMap.containsKey(button)) {
      return buttonsMap.get(button);
    }

    Trigger joystickButton = new Trigger(() -> joystick.getRawButton(button.getID()));
    buttonsMap.put(button, joystickButton);

    return joystickButton;
  }
}
