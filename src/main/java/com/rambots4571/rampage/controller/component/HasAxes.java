package com.rambots4571.rampage.controller.component;

public interface HasAxes<A extends Enum<A> & IAxis> {
  double getRawAxis(int axis);

  default double getAxisValue(A axis) {
    return axis.isInverted() ? -getRawAxis(axis.getNumber()) : getRawAxis(axis.getNumber());
  }
}
