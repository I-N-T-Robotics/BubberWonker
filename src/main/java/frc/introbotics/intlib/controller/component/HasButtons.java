package frc.introbotics.intlib.controller.component;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public interface HasButtons<B extends Enum<B> & Mappable> {
  Buttons<B> getButtonsMap();

  default Trigger getButton(B button) {
    return getButtonsMap().get(button);
  }
}
