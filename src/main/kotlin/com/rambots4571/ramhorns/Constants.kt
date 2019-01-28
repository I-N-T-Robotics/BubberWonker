package com.rambots4571.ramhorns

object Constants {
    object Talon {
        const val timeoutMs = 100
        const val trajectoryPointPeriod = 100
        const val longTimeoutMs = 1000
        const val MIN_POINTS_IN_TALON = 5
        const val MAX_BTM_BUFFER_COUNT = 128
        const val MAX_TOP_BUFFER_COUNT = 512
    }

    object Paths {
        const val dir = "home/lvuser/deploy/paths"
        const val leftSuffix = "_left.csv"
        const val rightSuffix = "_right.csv"
    }
}