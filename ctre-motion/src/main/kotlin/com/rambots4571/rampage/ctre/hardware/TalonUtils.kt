package com.rambots4571.rampage.ctre.hardware

import com.ctre.phoenix.ErrorCode
import edu.wpi.first.wpilibj.DriverStation

fun checkError(errorCode: ErrorCode, message: String) {
    if (errorCode != ErrorCode.OK) {
        DriverStation.reportError(message + errorCode, false)
    }
}