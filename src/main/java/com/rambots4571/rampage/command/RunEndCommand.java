package com.rambots4571.rampage.command;

import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * A Command that is like {@link edu.wpi.first.wpilibj2.command.RunCommand}. It takes a runnable
 * that runs continuously but WITH an end() function.
 */
public class RunEndCommand extends FunctionalCommand {
  private static final Runnable doNothing = () -> {};

  /** Creates a new RunEndCommand. */
  public RunEndCommand(
      Runnable loopFunc,
      Consumer<Boolean> endFunc,
      BooleanSupplier isFinished,
      Subsystem... subsystems) {
    super(doNothing, loopFunc, endFunc, isFinished, subsystems);
  }

  public RunEndCommand(Runnable loopFunc, Runnable endFunc, Subsystem... subsystems) {
    super(doNothing, loopFunc, ignore -> endFunc.run(), () -> false, subsystems);
  }

  public RunEndCommand(
      Runnable loopFunc, Runnable endFunc, BooleanSupplier isFinished, Subsystem... subsystems) {
    super(doNothing, loopFunc, ignore -> endFunc.run(), isFinished, subsystems);
  }
}
