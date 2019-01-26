package com.rambots4571.ramhorns.hardware

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.rambots4571.ramhorns.Constants

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

    fun configurePIDF(kP: Double, kI: Double, kD: Double, kF: Double,
                      kPIDSlotIdx: Int = 0,
                      timeoutMs: Int = Constants.Talon.timeoutMs) {
        config_kP(kPIDSlotIdx, kP, timeoutMs)
        config_kI(kPIDSlotIdx, kI, timeoutMs)
        config_kD(kPIDSlotIdx, kD, timeoutMs)
        config_kF(kPIDSlotIdx, kF, timeoutMs)
    }
}