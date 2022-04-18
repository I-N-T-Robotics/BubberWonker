package com.rambots4571.rampage.joystick;

import com.rambots4571.rampage.joystick.component.IAxis;
import com.rambots4571.rampage.joystick.component.Mappable;

public class Gamepad {
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

	public static Controller<Gamepad.Button, Gamepad.Axis> make(int port) {
		return new Controller<Gamepad.Button, Gamepad.Axis>(port);
	}
}
