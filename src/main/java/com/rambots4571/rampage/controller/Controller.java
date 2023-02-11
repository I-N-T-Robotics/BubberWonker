package com.rambots4571.rampage.controller;

import com.rambots4571.rampage.controller.component.Buttons;
import com.rambots4571.rampage.controller.component.DPadHandler;
import com.rambots4571.rampage.controller.component.HasAxes;
import com.rambots4571.rampage.controller.component.HasButtons;
import com.rambots4571.rampage.controller.component.HasDPad;
import com.rambots4571.rampage.controller.component.IAxis;
import com.rambots4571.rampage.controller.component.Mappable;

import edu.wpi.first.wpilibj.GenericHID;

public class Controller<B extends Enum<B> & Mappable, A extends Enum<A> & IAxis> extends GenericHID
    implements HasButtons<B>, HasAxes<A>, HasDPad {
  private final Buttons<B> buttons;
  private final DPadHandler dPadHandler;

  public Controller(int port) {
    super(port);
    buttons = new Buttons<>(this);
    dPadHandler = new DPadHandler(this);
  }

  @Override
  public Buttons<B> getButtonsMap() {
    return buttons;
  }

  @Override
  public DPadHandler getDPadHandler() {
    return dPadHandler;
  }
}
