package com.rambots4571.rampage.hardware

import edu.wpi.first.wpilibj.DoubleSolenoid

class DoubleSolenoid(forwardChannel: Int, reverseChannel: Int) :
        DoubleSolenoid(forwardChannel, reverseChannel) {

    fun toggle() {
        val currentValue =
            if (get() == Value.kForward) Value.kReverse else Value.kForward
        set(currentValue)
    }
}