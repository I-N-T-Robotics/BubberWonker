package com.rambots4571.rampage.joystick.component;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.HashMap;

public class Buttons<B extends Enum<B> & Mappable> {
	private final HashMap<B, JoystickButton> buttonsMap;
	private final Joystick joystick;

	public Buttons(Joystick joystick) {
		buttonsMap = new HashMap<>();
		this.joystick = joystick;
	}

	public JoystickButton get(B button) {
		if (buttonsMap.containsKey(button)) {
			return buttonsMap.get(button);
		}

		JoystickButton joystickButton = new JoystickButton(joystick, button.getID());
		buttonsMap.put(button, joystickButton);

		return joystickButton;
	}
}
