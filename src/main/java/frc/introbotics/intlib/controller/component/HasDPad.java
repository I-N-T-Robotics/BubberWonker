package frc.introbotics.intlib.controller.component;

import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.introbotics.intlib.controller.component.DPadButton.Direction;

public interface HasDPad {
  DPadHandler getDPadHandler();

  default Trigger getDPadButton(Direction direction) {
    return getDPadHandler().get(direction);
  }
}
