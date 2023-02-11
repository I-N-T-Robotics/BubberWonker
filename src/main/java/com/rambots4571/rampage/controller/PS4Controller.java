package com.rambots4571.rampage.controller;

import com.rambots4571.rampage.controller.component.Buttons;
import com.rambots4571.rampage.controller.component.DPadHandler;
import com.rambots4571.rampage.controller.component.HasAxes;
import com.rambots4571.rampage.controller.component.HasButtons;
import com.rambots4571.rampage.controller.component.HasDPad;
import com.rambots4571.rampage.controller.component.IAxis;
import com.rambots4571.rampage.controller.component.Mappable;

import edu.wpi.first.wpilibj.GenericHID;

public class PS4Controller extends GenericHID
    implements HasButtons<PS4Controller.Button>, HasAxes<PS4Controller.Axis>, HasDPad {
  private final Buttons<Button> buttons;
  private final DPadHandler dPadHandler;

  public PS4Controller(int port) {
    super(port);
    this.buttons = new Buttons<>(this);
    this.dPadHandler = new DPadHandler(this);
  }

  public static enum Button implements Mappable {
    Square(1),
    Cross(2),
    Circle(3),
    Triangle(4),
    L1(5),
    R1(6),
    L2(7),
    R2(8),
    Share(9),
    Options(10),
    L3(11),
    R3(12),
    PS(13),
    Touchpad(14);

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
    LeftX(0),
    LeftY(1, true),
    RightX(2),
    RightY(5, true),
    L2(3),
    R2(4);

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
