package com.rambots4571.rampage.joystick;

import com.rambots4571.rampage.joystick.component.Buttons;
import com.rambots4571.rampage.joystick.component.DPadButton;
import com.rambots4571.rampage.joystick.component.DPadButton.Direction;
import com.rambots4571.rampage.joystick.component.HasAxes;
import com.rambots4571.rampage.joystick.component.HasButtons;
import com.rambots4571.rampage.joystick.component.HasDPad;
import com.rambots4571.rampage.joystick.component.IAxis;
import com.rambots4571.rampage.joystick.component.Mappable;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.HashMap;

public class Gamepad extends Joystick implements HasButtons, HasAxes, HasDPad {
	private final Buttons<Gamepad.ButtonType> buttons;
	private final HashMap<Direction, Button> dPadButtons;

	public static enum ButtonType implements Mappable {
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

	public static enum Axis implements IAxis {
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
	 * port on the drivers station.
	 *
	 * @param port The port on the Driver Station that the joystick is plugged
	 *             into.
	 */
	public Gamepad(int port) {
		super(port);
		buttons = new Buttons<>(this);
		dPadButtons = new HashMap<>();
	}

	@Override
	public JoystickButton getButton(Mappable button) {
		return buttons.get((ButtonType) button);
	}

	@Override
	public Button getDPadButton(Direction direction) {
		if (dPadButtons.containsKey(direction))
			return dPadButtons.get(direction);

		Button button = new DPadButton(this, direction);
		dPadButtons.put(direction, button);

		return button;
	}

	@Override
	public double getAxisValue(IAxis axis) {
		return axis.isInverted() ? -getRawAxis(axis.getID()) : getRawAxis(axis.getID());
	}
}
