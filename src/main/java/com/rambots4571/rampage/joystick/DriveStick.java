package com.rambots4571.rampage.joystick;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriveStick extends Joystick {
    private final HashMap<DriveStick.ButtonType, JoystickButton> buttons;

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

        private int id;

        ButtonType(int id) {
            this.id = id;
        }

        @Override
        public int getID() {
            return id;
        }
    }

    public DriveStick(int port) {
        super(port);
        buttons = Controller.getButtons(this, DriveStick.ButtonType.values());
    }

    public JoystickButton get(ButtonType button) {
        return buttons.get(button);
    }

    public double getXAxis() {
        return getRawAxis(0);
    }

    public double getYAxis() {
        // inverted because it returns negative going forward
        return -getRawAxis(1);
    }

    public double getZAxis() {
        return getRawAxis(2);
    }

    public double getSlider() {
        return getRawAxis(3);
    }
}
