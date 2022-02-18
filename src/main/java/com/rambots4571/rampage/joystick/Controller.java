package com.rambots4571.rampage.joystick;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller<B extends Enum<B> & Mappable> extends Joystick {
    private final HashMap<B, JoystickButton> buttons = new HashMap<>();

    public Controller(final int port, B values[]) {
        super(port);
        for (B button : values) {
            buttons.put(button, new JoystickButton(this, button.getID()));
        }
    }

    public JoystickButton getButton(B button) {
        return buttons.get(button);
    }

    public static <B extends Enum<B> & Mappable> HashMap<B, JoystickButton> getButtons(Joystick stick, B values[]) {
        HashMap<B, JoystickButton> buttons = new HashMap<>();

        for (B button : values) {
            buttons.put(button, new JoystickButton(stick, button.getID()));
        }

        return buttons;
    }
}
