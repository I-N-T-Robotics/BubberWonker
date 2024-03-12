package frc.introbotics.intlib.controller.component;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.HashMap;

public class Buttons<B extends Enum<B> & Mappable> {
  private final HashMap<B, Trigger> buttonsMap;
  private final GenericHID controller;

  public Buttons(GenericHID controller) {
    buttonsMap = new HashMap<>();
    this.controller = controller;
  }

  public Trigger get(B button) {
    if (buttonsMap.containsKey(button)) {
      return buttonsMap.get(button);
    }

    Trigger trigger = new Trigger(() -> controller.getRawButton(button.getId()));
    buttonsMap.put(button, trigger);

    return trigger;
  }
}
