package com.rambots4571.rampage.joystick

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.button.JoystickButton

class Button(joystick: GenericHID, buttonNumber: Int) :
        JoystickButton(joystick, buttonNumber) {

    fun whenPressed(action: Runnable) {
        whenPressed(InstantCommand(action))
    }
}