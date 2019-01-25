package com.rambots4571.ramhorns.motionprofile

import com.ctre.phoenix.motion.TrajectoryPoint
import com.rambots4571.ramhorns.Constants
import edu.wpi.first.wpilibj.DriverStation
import java.io.File
import java.io.FileNotFoundException
import java.util.*

object Parser {
    private var ticksPerUnit: Double? = null

    fun getPoints(fileName: String, dir: String = Constants.Paths.dir,
                  skipFirstLine: Boolean = false): ArrayList<TrajectoryPoint> {
        val file = File(dir + fileName)
        val list = ArrayList<TrajectoryPoint>()
        val point = TrajectoryPoint()
        try {
            val reader = Scanner(file)
            if (skipFirstLine) reader.nextLine()
            while (reader.hasNextLine()) {
                val values = reader.nextLine().split(", ")
                point.position = values[0].toDouble() * ticksPerUnit!!
                point.velocity = values[1].toDouble() * ticksPerUnit!! / 10
                point.headingDeg = 0.0
                point.profileSlotSelect0 = 0
                point.profileSlotSelect1 = 0
                point.timeDur = values[2].toInt()
                list.add(point)
            }
        } catch (e: FileNotFoundException) {
            println("can't find file")
            DriverStation.reportWarning("can't find file", false)
            println(e.stackTrace)
        }
        return list
    }
}