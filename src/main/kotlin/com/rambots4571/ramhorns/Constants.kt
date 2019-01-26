package com.rambots4571.ramhorns

object Constants {
    object Talon {
        const val timeoutMs = 100
        const val timeout = 0.1
        const val MIN_POINTS_IN_TALON = 5
        const val MAX_BTM_BUFFER_COUNT = 128
        const val MAX_TOP_BUFFER_COUNT = 512
        const val MOTION_CONTROL_FRAME_PERIOD = timeoutMs / 2
        const val PIDSlotIdx = 0
    }

    object Paths {
        const val dir = "home/lvuser/deploy/paths"
        const val leftSuffix = "_left.csv"
        const val rightSuffix = "_right.csv"
    }
}