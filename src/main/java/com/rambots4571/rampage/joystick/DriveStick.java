package com.rambots4571.rampage.joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriveStick extends Joystick {
    private final JoystickButton button1 = new JoystickButton(this, 1);
    private final JoystickButton button2 = new JoystickButton(this, 2);
    private final JoystickButton button3 = new JoystickButton(this, 3);
    private final JoystickButton button4 = new JoystickButton(this, 4);
    private final JoystickButton button5 = new JoystickButton(this, 5);
    private final JoystickButton button6 = new JoystickButton(this, 6);
    private final JoystickButton button7 = new JoystickButton(this, 7);
    private final JoystickButton button8 = new JoystickButton(this, 8);
    private final JoystickButton button9 = new JoystickButton(this, 9);
    private final JoystickButton button10 = new JoystickButton(this, 10);
    private final JoystickButton button11 = new JoystickButton(this, 11);
    private final JoystickButton button12 = new JoystickButton(this, 12);

    /**
     * Construct an instance of a joystick. The joystick index is the USB
     * port on the drivers
     * station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public DriveStick(int port) {
        super(port);
    }

    public JoystickButton getButton1() {
        return button1;
    }

    public JoystickButton getButton2() {
        return button2;
    }

    public JoystickButton getButton3() {
        return button3;
    }

    public JoystickButton getButton4() {
        return button4;
    }

    public JoystickButton getButton5() {
        return button5;
    }

    public JoystickButton getButton6() {
        return button6;
    }

    public JoystickButton getButton7() {
        return button7;
    }

    public JoystickButton getButton8() {
        return button8;
    }

    public JoystickButton getButton9() {
        return button9;
    }

    public JoystickButton getButton10() {
        return button10;
    }

    public JoystickButton getButton11() {
        return button11;
    }

    public JoystickButton getButton12() {
        return button12;
    }

    public double getXAxis() {
        return getRawAxis(0);
    }

    public double getYAxis() {
        // inverted because ot returns negative going forward
        return -getRawAxis(1);
    }

    public double getZAxis() {
        return getRawAxis(2);
    }

    public double getSlider() {
        return getRawAxis(3);
    }
}
