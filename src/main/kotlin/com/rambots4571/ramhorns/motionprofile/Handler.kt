package com.rambots4571.ramhorns.motionprofile

import com.ctre.phoenix.motion.MotionProfileStatus
import com.ctre.phoenix.motion.SetValueMotionProfile
import com.ctre.phoenix.motorcontrol.ControlMode
import com.rambots4571.ramhorns.Constants
import com.rambots4571.ramhorns.hardware.LazyTalonSRX
import edu.wpi.first.wpilibj.Notifier

internal class Handler(
    private val profile: Profile, private val leftTalon: LazyTalonSRX, private val rightTalon: LazyTalonSRX) {
    private val talons = arrayOf(leftTalon, rightTalon)
    private val executorThread: Notifier
    private val bufferThread: Notifier
    private var executionState: ExecutionState
    private var started = false
    private var pointIndex = 0
    val statuses = Array(talons.size) { MotionProfileStatus() }
    var currentMode: SetValueMotionProfile? = null
        private set
    var isFinished = false
        private set

    init {
        executionState = ExecutionState.WAITING
        bufferThread = Notifier(PeriodicBufferProcessor())
        bufferThread.startPeriodic(profile.motionControlFramePeriod * 1000.0)
        talons.forEach { it.changeMotionControlFramePeriod(profile.motionControlFramePeriod) }
        executorThread = Notifier(PeriodicExecutor())
    }

    enum class ExecutionState {
        WAITING, STARTED, EXECUTING
    }

    fun reset() {
        talons.forEach { it.clearMotionProfileTrajectories() }
        executionState = ExecutionState.WAITING
        started = false
    }

    fun execute() {
        executorThread.startPeriodic(0.025)
        started = true
    }

    fun onInterrupt() {
        bufferThread.stop()
        executorThread.stop()
        setMode(SetValueMotionProfile.Disable)
    }

    private fun onFinished() {
        isFinished = true
        bufferThread.stop()
        executorThread.stop()
        talons.forEach { it.clearMotionProfileTrajectories() }
    }

    fun manage() {
        fillTalons()
        updateMPStatuses()
        var readyToProgress = true
        when (executionState) {
            ExecutionState.WAITING ->
                if (started) {
                    started = false
                    setMode(SetValueMotionProfile.Disable)
                    executionState = ExecutionState.STARTED
                }
            ExecutionState.STARTED -> {
                for (status in statuses) {
                    if (status.btmBufferCnt <= Constants.Talon.MIN_POINTS_IN_TALON) {
                        readyToProgress = false
                    }
                }
                if (readyToProgress) {
                    setMode(SetValueMotionProfile.Enable)
                    executionState = ExecutionState.EXECUTING
                }
            }
            ExecutionState.EXECUTING -> {
                readyToProgress = true
                statuses.forEach {
                    if (!it.activePointValid || !it.isLast) {
                        readyToProgress = false
                    }
                }
                if (readyToProgress) {
                    onFinished()
                }
            }
        }
    }

    fun fillTalons() {
        if (pointIndex == 0) {
            talons.forEach {
                it.clearMotionProfileTrajectories()
                it.configMotionProfileTrajectoryPeriod(profile.trajectoryPointPeriod, profile.timeoutMs)
                it.clearMotionProfileHasUnderrun(profile.timeoutMs)
            }
        }
        updateMPStatuses()
        var maxFilled = statuses[0].btmBufferCnt
        for (status in statuses) {
            if (status.topBufferCnt > maxFilled) {
                maxFilled = status.topBufferCnt
            }
        }
        var numPointsToFill = Constants.Talon.MAX_TOP_BUFFER_COUNT - maxFilled
        isFinished = false
        while (!isFinished && numPointsToFill > 0) {
            if (pointIndex >= profile.length) {
                isFinished = true
                break
            }
            profile.leftProfile[pointIndex].zeroPos = false
            profile.rightProfile[pointIndex].zeroPos = false
            if (pointIndex == 0) {
                profile.leftProfile[pointIndex].zeroPos = true
                profile.rightProfile[pointIndex].zeroPos = true
            }
            profile.leftProfile[pointIndex].isLastPoint = false
            profile.rightProfile[pointIndex].isLastPoint = false
            if (pointIndex + 1 == profile.length) {
                profile.leftProfile[pointIndex].isLastPoint = true
                profile.rightProfile[pointIndex].isLastPoint = true
            }
            leftTalon.pushMotionProfileTrajectory(profile.leftProfile[pointIndex])
            rightTalon.pushMotionProfileTrajectory(profile.rightProfile[pointIndex])
            pointIndex++
            numPointsToFill--
        }
        updateMPStatuses()
    }

    fun updateMPStatuses() {
        for (i in 0 until talons.size) {
            talons[i].getMotionProfileStatus(statuses[i])
        }
    }

    private fun setMode(mode: SetValueMotionProfile) {
        currentMode = mode
        talons.forEach {
            it.set(ControlMode.MotionProfile, mode.value.toDouble())
        }
    }

    private inner class PeriodicBufferProcessor : Runnable {
        override fun run() {
            for (i in 0 until statuses.size) {
                if (statuses[i].btmBufferCnt < Constants.Talon.MAX_BTM_BUFFER_COUNT) {
                    talons[i].processMotionProfileBuffer()
                }
            }
        }
    }

    private inner class PeriodicExecutor : Runnable {
        override fun run() = manage()
    }
}