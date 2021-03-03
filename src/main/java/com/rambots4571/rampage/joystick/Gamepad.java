package com.rambots4571.rampage.joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Gamepad extends Joystick {
    private final JoystickButton a = new JoystickButton(this, 1);
    private final JoystickButton b = new JoystickButton(this, 2);
    private final JoystickButton x = new JoystickButton(this, 3);
    private final JoystickButton y = new JoystickButton(this, 4);
    private final JoystickButton leftBumper = new JoystickButton(this, 5);
    private final JoystickButton rightBumper = new JoystickButton(this, 6);
    private final JoystickButton backButton = new JoystickButton(this, 7);
    private final JoystickButton startButton = new JoystickButton(this, 8);
    private final JoystickButton leftStick = new JoystickButton(this, 9);
    private final JoystickButton rightStick = new JoystickButton(this, 10);

    /**
     * Construct an instance of a joystick. The joystick index is the USB
     * port on the drivers
     * station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public Gamepad(int port) {
        super(port);
    }

    public JoystickButton getButtonA() {
        return a;
    }

    public JoystickButton getButtonB() {
        return b;
    }

    public JoystickButton getButtonX() {
        return x;
    }

    public JoystickButton getButtonY() {
        return y;
    }

    public JoystickButton getLeftBumper() {
        return leftBumper;
    }

    public JoystickButton getRightBumper() {
        return rightBumper;
    }

    public JoystickButton getBackButton() {
        return backButton;
    }

    public JoystickButton getStartButton() {
        return startButton;
    }

    public JoystickButton getLeftStick() {
        return leftStick;
    }

    public JoystickButton getRightStick() {
        return rightStick;
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
