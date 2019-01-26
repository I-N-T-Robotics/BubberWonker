package com.rambots4571.ramhorns.hardware

import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.ControlFrame
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced
import com.ctre.phoenix.ParamEnum
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal
import com.ctre.phoenix.motorcontrol.LimitSwitchSource
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX

data class Configuration(val neutralMode: NeutralMode = NeutralMode.Brake,
                         val neutralDeadband: Double = 0.04,
                         val enableCurrentLimit: Boolean = false,
                         val enableSoftLimit: Boolean = false,
                         val enableLimitSwitch: Boolean = false,
                         val forwardSoftLimit: Int = 0,
                         val reverseSoftLimit: Int = 0,
                         val inverted: Boolean = false,
                         val sensorOutOfPhase: Boolean = false,
                         val controlFramePeriodMs: Int = 5,
                         val motionControlFramePeriodMs: Int = 100,
                         val generalStatusFrameRateMs: Int = 100,
                         val feedbackStatusFrameRateMs: Int = 100,
                         val magEncoderStatusFrameRateMs: Int = 100,
                         val analogTempVBatStatusFrameRateMs: Int = 100,
                         val pulseWidthStatusFrameRateMs: Int = 100,
                         val velocityMeasPeriod: VelocityMeasPeriod =
                            VelocityMeasPeriod.Period_100Ms,
                         val velMeasRollingAvgWindow: Int = 64,
                         val openLoopRampRate: Double = 0.0,
                         val closedLoopRampRate: Double = 0.0)

object TalonSRXFactory {
    private const val kTimeoutMs = 100
    private val defaultConfig = Configuration()
    private val followerConfig = Configuration(controlFramePeriodMs = 100,
                                               motionControlFramePeriodMs = 1000,
                                               generalStatusFrameRateMs = 1000,
                                               feedbackStatusFrameRateMs = 1000,
                                               magEncoderStatusFrameRateMs = 1000,
                                               analogTempVBatStatusFrameRateMs = 1000,
                                               pulseWidthStatusFrameRateMs = 1000)

    fun createTalon(id: Int, config: Configuration): LazyTalonSRX {
        val talon = LazyTalonSRX(id)
        talon.set(ControlMode.PercentOutput, 0.0)

        talon.changeMotionControlFramePeriod(config.motionControlFramePeriodMs)
        talon.clearMotionProfileHasUnderrun(kTimeoutMs)
        talon.clearMotionProfileTrajectories()

        talon.clearStickyFaults(kTimeoutMs)

        talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                                             LimitSwitchNormal.NormallyOpen,
                                             kTimeoutMs)
        talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                                             LimitSwitchNormal.NormallyOpen,
                                             kTimeoutMs)
        talon.overrideLimitSwitchesEnable(config.enableLimitSwitch)

        // Turn off re-zeroing by default.
        talon.configSetParameter(
                ParamEnum.eClearPositionOnLimitF, 0.0, 0, 0, kTimeoutMs)
        talon.configSetParameter(
                ParamEnum.eClearPositionOnLimitR, 0.0, 0, 0, kTimeoutMs)

        talon.configNominalOutputForward(0.0, kTimeoutMs)
        talon.configNominalOutputReverse(0.0, kTimeoutMs)
        talon.configNeutralDeadband(config.neutralDeadband, kTimeoutMs)

        talon.configPeakOutputForward(1.0, kTimeoutMs)
        talon.configPeakOutputReverse(-1.0, kTimeoutMs)

        talon.setNeutralMode(config.neutralMode)

        talon.configForwardSoftLimitThreshold(config.forwardSoftLimit, kTimeoutMs)
        talon.configForwardSoftLimitEnable(config.enableSoftLimit, kTimeoutMs)

        talon.configReverseSoftLimitThreshold(config.reverseSoftLimit, kTimeoutMs)
        talon.configReverseSoftLimitEnable(config.enableSoftLimit, kTimeoutMs)
        talon.overrideSoftLimitsEnable(config.enableSoftLimit)

        talon.inverted = config.inverted
        talon.setSensorPhase(config.sensorOutOfPhase)

        talon.selectProfileSlot(0, 0)

        talon.configVelocityMeasurementPeriod(config.velocityMeasPeriod,
                                              kTimeoutMs)
        talon.configVelocityMeasurementWindow(
                config.velMeasRollingAvgWindow, kTimeoutMs)

        talon.configOpenloopRamp(config.openLoopRampRate, kTimeoutMs)
        talon.configClosedloopRamp(config.closedLoopRampRate, kTimeoutMs)

        talon.configVoltageCompSaturation(0.0, kTimeoutMs)
        talon.configVoltageMeasurementFilter(32, kTimeoutMs)
        talon.enableVoltageCompensation(false)

        talon.enableCurrentLimit(config.enableCurrentLimit)

        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General,
                                   config.generalStatusFrameRateMs,
                                   kTimeoutMs)
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0,
                                   config.feedbackStatusFrameRateMs,
                                   kTimeoutMs)

        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature,
                                   config.magEncoderStatusFrameRateMs,
                                   kTimeoutMs)
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_4_AinTempVbat,
                                   config.analogTempVBatStatusFrameRateMs,
                                   kTimeoutMs)
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_8_PulseWidth,
                                   config.pulseWidthStatusFrameRateMs,
                                   kTimeoutMs)

        talon.setControlFramePeriod(ControlFrame.Control_3_General,
                                    config.controlFramePeriodMs)

        return talon
    }

    fun createDefaultTalon(id: Int): LazyTalonSRX {
        return createTalon(id, defaultConfig)
    }

    fun createFollowerTalon(id: Int, masterId: Int): LazyTalonSRX {
        val talon = createTalon(id, followerConfig)
        talon.set(ControlMode.Follower, masterId.toDouble())
        return talon
    }
}