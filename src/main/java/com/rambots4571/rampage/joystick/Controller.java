package com.rambots4571.rampage.joystick;

import com.rambots4571.rampage.joystick.component.Buttons;
import com.rambots4571.rampage.joystick.component.DPadButton.Direction;
import com.rambots4571.rampage.joystick.component.DPadHandler;
import com.rambots4571.rampage.joystick.component.HasAxes;
import com.rambots4571.rampage.joystick.component.HasButtons;
import com.rambots4571.rampage.joystick.component.HasDPad;
import com.rambots4571.rampage.joystick.component.IAxis;
import com.rambots4571.rampage.joystick.component.Mappable;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller<B extends Enum<B> & Mappable, A extends Enum<A> & IAxis> extends Joystick
    implements HasButtons<B>, HasAxes<A>, HasDPad {
  private final Buttons<B> buttons;
  private final DPadHandler dPadHandler;

  public Controller(int port) {
    super(port);
    buttons = new Buttons<>(this);
    dPadHandler = new DPadHandler(this);
  }

  @Override
  public JoystickButton getButton(B button) {
    return buttons.get(button);
  }

  @Override
  public Button getDPadButton(Direction direction) {
    return dPadHandler.get(direction);
  }

  @Override
  public double getAxisValue(A axis) {
    return axis.isInverted() ? -getRawAxis(axis.getNumber()) : getRawAxis(axis.getNumber());
  }

  public static <X extends Enum<X> & Mappable, Y extends Enum<Y> & IAxis> Controller<X, Y> make(
      int port) {
    return new Controller<X, Y>(port);
  }
}
