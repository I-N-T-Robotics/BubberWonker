package com.rambots4571.rampage.joystick;

import com.rambots4571.rampage.joystick.component.Buttons;
import com.rambots4571.rampage.joystick.component.DPadHandler;
import com.rambots4571.rampage.joystick.component.HasAxes;
import com.rambots4571.rampage.joystick.component.HasButtons;
import com.rambots4571.rampage.joystick.component.HasDPad;
import com.rambots4571.rampage.joystick.component.IAxis;
import com.rambots4571.rampage.joystick.component.Mappable;

import edu.wpi.first.wpilibj.GenericHID;

public class DriveStick extends GenericHID
    implements HasButtons<DriveStick.Button>, HasAxes<DriveStick.Axis>, HasDPad {
  private final Buttons<Button> buttons;
  private final DPadHandler dPadHandler;

  public DriveStick(int port) {
    super(port);
    this.buttons = new Buttons<>(this);
    this.dPadHandler = new DPadHandler(this);
  }

  public static enum Button implements Mappable {
    button1(1),
    button2(2),
    button3(3),
    button4(4),
    button5(5),
    button6(6),
    button7(7),
    button8(8),
    button9(9),
    button10(10),
    button11(11),
    button12(12);

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
    xAxis(0),
    yAxis(1, true),
    zAxis(2),
    Slider(3);

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
    public boolean isInverted() {
      return inverted;
    }

    @Override
    public int getNumber() {
      return number;
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
