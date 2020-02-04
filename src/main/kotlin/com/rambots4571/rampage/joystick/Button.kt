package com.rambots4571.rampage.joystick

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj2.command.button.JoystickButton

class Button(joystick: GenericHID, buttonNumber: Int) :
        JoystickButton(joystick, buttonNumber) {
    private var toggle = false

    fun toggle(firstAction: Runnable, secondAction: Runnable) : Button {
        whenPressed(Runnable {
            if (toggle) {
                secondAction.run()
            } else {
                firstAction.run()
            }
            toggle = !toggle
        })
        return this
    }
}