package com.rambots4571.rampage.joystick

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj2.command.button.JoystickButton

class Button(joystick: GenericHID, buttonNumber: Int) :
        JoystickButton(joystick, buttonNumber) {
    fun toggle(
        action: Runnable, defaultAction: Runnable,
        vararg subsystem: SubsystemBase): Button {
        var toggle = false
        whenPressed(object : InstantCommand() {
            init {
                requirements.addAll(subsystem)
            }

            override fun initialize() {
                if (toggle) {
                    defaultAction.run()
                } else {
                    action.run()
                }
                toggle = !toggle
            }

            override fun end(interrupted: Boolean) {
                if (interrupted) {
                    toggle = false
                    cancel()
                }
            }
        })
        return this
    }
}