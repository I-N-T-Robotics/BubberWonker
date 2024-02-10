package frc.introbotics.intlib.swerve;

import edu.wpi.first.math.geometry.Rotation2d;

import lombok.Value;

@Value
public class SwerveModuleConstants {
  private int driveMotorID;
  private int angleMotorID;
  private int cancoderID;
  private Rotation2d angleOffset;
}
