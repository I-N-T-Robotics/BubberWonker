package com.rambots4571.ramhorns.hardware

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX

class LazyTalonSRX(port: Int) : TalonSRX(port) {
    protected var lastSet = Double.NaN
    protected var lastControlMode: ControlMode? = null

    override fun set(mode: ControlMode?, value: Double) {
        if (value != lastSet || mode != lastControlMode) {
            lastSet = value
            lastControlMode = mode
            super.set(mode, value)
        }
    }
}