package com.rambots4571.ramhorns.motionprofile

import com.ctre.phoenix.motion.TrajectoryPoint
import com.rambots4571.ramhorns.Constants
import com.rambots4571.ramhorns.hardware.LazyTalonSRX
import edu.wpi.first.wpilibj.DriverStation

class Profile(
    val leftProfile: ArrayList<TrajectoryPoint>, val rightProfile: ArrayList<TrajectoryPoint>, leftTalon: LazyTalonSRX,
    rightTalon: LazyTalonSRX) {
    val length: Int = leftProfile.size
    private val handler: Handler = Handler(this, leftTalon, rightTalon)
    var timeoutMs = Constants.Talon.timeoutMs
    var trajectoryPointPeriod = Constants.Talon.trajectoryPointPeriod
    var motionControlFramePeriod = timeoutMs / 2
    val isFinished = handler.isFinished

    fun execute() {
        if (leftProfile.size != 0 && rightProfile.size != 0) {
            handler.execute()
        } else {
            DriverStation.getInstance()
            DriverStation.reportError("Tried to run empty profile!", false)
        }
    }

    fun onInterrupt() = handler.onInterrupt()
}
