package com.rambots4571.rampage.joystick.component;

import com.rambots4571.rampage.joystick.component.DPadButton.Direction;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public interface HasDPad {
  Trigger getDPadButton(Direction direction);
}
