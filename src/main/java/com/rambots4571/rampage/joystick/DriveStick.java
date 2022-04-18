package com.rambots4571.rampage.joystick;

import com.rambots4571.rampage.joystick.component.IAxis;
import com.rambots4571.rampage.joystick.component.Mappable;

public class DriveStick {
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

	public static Controller<DriveStick.Button, DriveStick.Axis> make(int port) {
		return new Controller<DriveStick.Button, DriveStick.Axis>(port);
	}
}
