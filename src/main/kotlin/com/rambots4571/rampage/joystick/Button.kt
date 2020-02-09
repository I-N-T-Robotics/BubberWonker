package com.rambots4571.rampage.joystick

import com.rambots4571.rampage.function.Pair
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj2.command.button.JoystickButton

class Button(joystick: GenericHID, buttonNumber: Int) :
        JoystickButton(joystick, buttonNumber) {
    fun toggle(
        action: Runnable, defaultAction: Runnable,
        vararg subsystem: SubsystemBase): Button {
        var pair = Pair(action, defaultAction)
        val initialState = pair.copy()
        (object : CommandBase() {
            private var prevButton = get()

            init {
                requirements.addAll(subsystem)
            }

            override fun execute() {
                val currentButton = get()
                if (!prevButton && currentButton) pair.swap()
                pair.first.run()
            }

            override fun isFinished(): Boolean = false

            override fun end(interrupted: Boolean) {
                pair = initialState
            }
        }).schedule()
        return this
    }
}