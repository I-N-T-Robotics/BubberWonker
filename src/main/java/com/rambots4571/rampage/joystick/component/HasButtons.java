package com.rambots4571.rampage.joystick.component;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public interface HasButtons<B extends Enum<B> & Mappable> {
  Trigger getButton(B button);
}
