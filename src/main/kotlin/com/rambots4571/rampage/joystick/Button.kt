package com.rambots4571.rampage.joystick

import com.rambots4571.rampage.function.Pair
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj2.command.button.JoystickButton

class Button(joystick: GenericHID, buttonNumber: Int) :
        JoystickButton(joystick, buttonNumber) {
    fun toggle(
        action: Runnable, defaultAction: Runnable,
        vararg subsystem: SubsystemBase): Button {
        var pair = Pair(action, defaultAction)
        val initialState = pair.copy()
        whenPressed(object : InstantCommand() {
            init {
                requirements.addAll(subsystem)
            }

            override fun initialize() {
                pair.swap()
                pair.second.run()
            }

            override fun end(interrupted: Boolean) {
                if (interrupted) pair = initialState
            }
        })
        return this
    }
}