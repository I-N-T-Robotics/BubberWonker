package com.rambots4571.rampage.command;

import edu.wpi.first.util.function.BooleanConsumer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import java.util.function.BooleanSupplier;

/**
 * A Command that is like {@link edu.wpi.first.wpilibj2.command.RunCommand}.
 * It takes a runnable that runs continuously but WITH an end() function.
 */
public class RunEndCommand extends CommandBase {
  private final Runnable loopFunc;
  private final BooleanConsumer endFunc;
  private final BooleanSupplier isFinished;

  /** Creates a new RunEndCommand. */
  public RunEndCommand(Runnable loopFunc, BooleanConsumer endFunc, BooleanSupplier isFinished,
    Subsystem... subsystems) {
    this.loopFunc = loopFunc;
    this.endFunc = endFunc;
    this.isFinished = isFinished;
    addRequirements(subsystems);
  }

  public RunEndCommand(Runnable loopFunc, Runnable endFunc, Subsystem... subsystems) {
    this.loopFunc = loopFunc;
    this.endFunc = b -> endFunc.run();
    this.isFinished = () -> false;
    addRequirements(subsystems);
  }

  public RunEndCommand(Runnable loopFunc, Runnable endFunc, BooleanSupplier isFinished,
    Subsystem... subsystems) {
    this.loopFunc = loopFunc;
    this.endFunc = b -> endFunc.run();
    this.isFinished = isFinished;
    addRequirements(subsystems);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    loopFunc.run();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    endFunc.accept(interrupted);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished.getAsBoolean();
  }
}
