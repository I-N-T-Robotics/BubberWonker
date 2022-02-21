package com.rambots4571.rampage.joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Gamepad extends Joystick {
	private final Buttons<Gamepad.ButtonType> buttons;

	public enum ButtonType implements Mappable {
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

		ButtonType(int id) {
			this.id = id;
		}

		@Override
		public int getID() {
			return id;
		}

	}

	public enum Axis implements Mappable, Invertible {
		LeftXAxis(0),
		LeftYAxis(1, true),
		LeftTrigger(2),
		RightTrigger(3),
		RightXAxis(4),
		RightYAxis(5, true);

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
		public int getID() {
			return id;
		}

		@Override
		public boolean isInverted() {
			return inverted;
		}
	}

	/**
	 * Construct an instance of a joystick. The joystick index is the USB
	 * port on the drivers
	 * station.
	 *
	 * @param port The port on the Driver Station that the joystick is plugged
	 *             into.
	 */
	public Gamepad(int port) {
		super(port);
		buttons = new Buttons<>(this);
	}

	public JoystickButton getButton(Gamepad.ButtonType button) {
		return buttons.get(button);
	}

	public double getAxisValue(Gamepad.Axis axis) {
		return axis.inverted ? -getRawAxis(axis.id) : getRawAxis(axis.id);
	}
}
