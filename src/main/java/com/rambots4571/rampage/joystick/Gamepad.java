package com.rambots4571.rampage.joystick;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Gamepad extends Joystick {
    private final HashMap<Gamepad.ButtonType, JoystickButton> buttons;

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

    /**
     * Construct an instance of a joystick. The joystick index is the USB
     * port on the drivers
     * station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public Gamepad(int port) {
        super(port);
        buttons = Controller.getButtons(this, Gamepad.ButtonType.values());
    }

    public JoystickButton get(Gamepad.ButtonType button) {
        return buttons.get(button);
    }

    public double getLeftXAxis() {
        return getRawAxis(0);
    }

    public double getLeftYAxis() {
        return -getRawAxis(1);
    }

    public double getLeftTrigger() {
        return getRawAxis(2);
    }

    public double getRightXAxis() {
        return getRawAxis(4);
    }

    public double getRightYAxis() {
        return -getRawAxis(5);
    }

    public double getRightTrigger() {
        return getRawAxis(3);
    }
}
