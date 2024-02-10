package frc.introbotics.intlib.controller;

import edu.wpi.first.wpilibj.GenericHID;

import frc.introbotics.intlib.controller.component.Buttons;
import frc.introbotics.intlib.controller.component.DPadHandler;
import frc.introbotics.intlib.controller.component.HasAxes;
import frc.introbotics.intlib.controller.component.HasButtons;
import frc.introbotics.intlib.controller.component.HasDPad;
import frc.introbotics.intlib.controller.component.IAxis;
import frc.introbotics.intlib.controller.component.Mappable;
import lombok.Getter;

@Getter
public class Controller<B extends Enum<B> & Mappable, A extends Enum<A> & IAxis> extends GenericHID
    implements HasButtons<B>, HasAxes<A>, HasDPad {
  private final Buttons<B> buttonsMap;
  private final DPadHandler dPadHandler;

  public Controller(int port) {
    super(port);
    buttonsMap = new Buttons<>(this);
    dPadHandler = new DPadHandler(this);
  }
}
