package com.rambots4571.ramhorns.motionprofile

import com.ctre.phoenix.motion.TrajectoryPoint
import com.rambots4571.ramhorns.Constants
import com.rambots4571.ramhorns.hardware.LazyTalonSRX
import edu.wpi.first.wpilibj.DriverStation

class Profile(
    pathName: String, leftTalon: LazyTalonSRX, rightTalon: LazyTalonSRX, leftPIDSlot: Int = 0, rightPIDSlot: Int = 0) {
    val length: Int
    val handler: Handler
    val leftProfile: ArrayList<TrajectoryPoint>
    val rightProfile: ArrayList<TrajectoryPoint>
    var timoutMs = Constants.Talon.timeoutMs
    var trajectoryPointPeriod = Constants.Talon.trajectoryPointPeriod
    var motionControlFramePeriod = timoutMs / 2
    var ticksPerUnit: Double? = Parser.ticksPerUnit
        set(value) {
            Parser.ticksPerUnit = value
        }
    
    init {
        leftProfile = Parser.getPoints(pathName + Constants.Paths.leftSuffix)
        rightProfile = Parser.getPoints(pathName + Constants.Paths.rightSuffix)
        length = leftProfile.size
        handler = Handler(this, leftTalon, rightTalon)
    }

    val isFinished = handler.isFinished

    fun execute() {
        if (leftProfile.size != 0 && rightProfile.size != 0) {
            handler.execute()
        } else {
            DriverStation.getInstance()
            DriverStation.reportError("Tried to run empty profile!", false)
        }
    }

    fun onInterrupt() {
        handler.onInterrupt()
    }
}
