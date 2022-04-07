package com.rambots4571.rampage.joystick;

import com.rambots4571.rampage.joystick.component.Buttons;
import com.rambots4571.rampage.joystick.component.HasAxes;
import com.rambots4571.rampage.joystick.component.HasButtons;
import com.rambots4571.rampage.joystick.component.IAxis;
import com.rambots4571.rampage.joystick.component.Mappable;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriveStick extends Joystick implements HasButtons, HasAxes {
	private final Buttons<DriveStick.ButtonType> buttons;

	public static enum ButtonType implements Mappable {
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

	public static enum Axis implements IAxis {
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
			return inverted;
		}

		@Override
		public int getID() {
			return id;
		}
	}

	public DriveStick(int port) {
		super(port);
		buttons = new Buttons<>(this);
	}

	@Override
	public JoystickButton getButton(Mappable button) {
		return buttons.get((ButtonType) button);
	}

	@Override
	public double getAxisValue(IAxis axis) {
		return axis.isInverted() ? -getRawAxis(axis.getID()) : getRawAxis(axis.getID());
	}
}
