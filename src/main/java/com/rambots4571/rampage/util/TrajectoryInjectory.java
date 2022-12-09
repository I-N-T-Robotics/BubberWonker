package com.rambots4571.rampage.util;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import java.io.IOException;

public final class TrajectoryInjectory {

  public static Trajectory getTrajectory(String path) {
    try {
      return TrajectoryUtil.fromPathweaverJson(
          Filesystem.getDeployDirectory().toPath().resolve(path));
    } catch (IOException exception) {
      DriverStation.reportError("Error opening \"" + path + "\"", exception.getStackTrace());

      System.err.print("Error opening \"" + path + "\"");
      System.out.print(exception.getStackTrace());
      System.exit(4571);

      return null;
    }
  }

  public static Trajectory getTrajectory(String... paths) {
    Trajectory trajectory = getTrajectory(paths[0]);

    for (int i = 1; i < paths.length; ++i) {

      trajectory = trajectory.concatenate(getTrajectory(paths[i]));
    }
    return trajectory;
  }
}
