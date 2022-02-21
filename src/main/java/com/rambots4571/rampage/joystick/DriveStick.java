package com.rambots4571.rampage.joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriveStick extends Joystick {
	private final Buttons<DriveStick.ButtonType> buttons;

	public enum ButtonType implements Mappable {
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

		ButtonType(int id) {
			this.id = id;
		}

		@Override
		public int getID() {
			return id;
		}
	}

	public enum Axis implements Mappable, Invertible {
		xAxis(0),
		yAxis(1, true),
		zAxis(2),
		Slider(3);

		private final int id;
		private final boolean inverted;

		Axis(int id, boolean inverted) {
			this.id = id;
			this.inverted = inverted;
		}

		Axis(int id) {
			this.id = id;
			this.inverted = false;
		}

		@Override
		public boolean isInverted() {
			return false;
		}

		@Override
		public int getID() {
			return 0;
		}
	}

	public DriveStick(int port) {
		super(port);
		buttons = new Buttons<>(this);
	}

	public JoystickButton getButton(DriveStick.ButtonType button) {
		return buttons.get(button);
	}

	public double getAxisValue(DriveStick.Axis axis) {
		return axis.inverted ? -getRawAxis(axis.id) : getRawAxis(axis.id);
	}
}
