package com.rambots4571.rampage.joystick;

import com.rambots4571.rampage.joystick.component.Buttons;
import com.rambots4571.rampage.joystick.component.DPadHandler;
import com.rambots4571.rampage.joystick.component.HasAxes;
import com.rambots4571.rampage.joystick.component.HasButtons;
import com.rambots4571.rampage.joystick.component.HasDPad;
import com.rambots4571.rampage.joystick.component.IAxis;
import com.rambots4571.rampage.joystick.component.Mappable;

import edu.wpi.first.wpilibj.GenericHID;

public class Gamepad extends GenericHID
    implements HasButtons<Gamepad.Button>, HasAxes<Gamepad.Axis>, HasDPad {
  private final Buttons<Button> buttons;
  private final DPadHandler dPadHandler;

  public Gamepad(int port) {
    super(port);
    this.buttons = new Buttons<>(this);
    this.dPadHandler = new DPadHandler(this);
  }

  public static enum Button implements Mappable {
    A(1),
    B(2),
    X(3),
    Y(4),
    LeftBumper(5),
    RightBumper(6),
    BackButton(7),
    StartButton(8),
    LeftStick(9),
    RightStick(10);

    private final int id;

    Button(int id) {
      this.id = id;
    }

    @Override
    public int getID() {
      return id;
    }
  }

  public static enum Axis implements IAxis {
    LeftXAxis(0),
    LeftYAxis(1, true),
    LeftTrigger(2),
    RightTrigger(3),
    RightXAxis(4),
    RightYAxis(5, true);

    private final int number;
    private final boolean inverted;

    Axis(int number, boolean inverted) {
      this.number = number;
      this.inverted = inverted;
    }

    Axis(int number) {
      this.number = number;
      this.inverted = false;
    }

    @Override
    public int getNumber() {
      return number;
    }

    @Override
    public boolean isInverted() {
      return inverted;
    }
  }

  @Override
  public Buttons<Button> getButtonsMap() {
    return buttons;
  }

  @Override
  public DPadHandler getDPadHandler() {
    return dPadHandler;
  }
}
