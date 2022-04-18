package com.rambots4571.rampage.joystick.component;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public interface HasButtons<B extends Enum<B> & Mappable> {
  JoystickButton getButton(B button);
}
