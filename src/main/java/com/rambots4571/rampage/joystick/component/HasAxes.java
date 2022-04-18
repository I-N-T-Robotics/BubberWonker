package com.rambots4571.rampage.joystick.component;

public interface HasAxes<A extends Enum<A> & IAxis> {
	double getAxisValue(A axis);
}
