package com.rambots4571.rampage.command;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class CommandBuilder {
  private Runnable init = () -> {};
  private Runnable execute = () -> {};
  private BooleanSupplier isFinished = () -> true;
  private Consumer<Boolean> end = interrupted -> {};
  private Subsystem[] requirements;

  public CommandBuilder() {}

  public CommandBuilder init(Runnable init) {
    this.init = init;
    return this;
  }

  public CommandBuilder execute(Runnable execute) {
    this.execute = execute;
    return this;
  }

  public CommandBuilder isFinished(BooleanSupplier isFinished) {
    this.isFinished = isFinished;
    return this;
  }

  public CommandBuilder isFinished(boolean bool) {
    this.isFinished = () -> bool;
    return this;
  }

  public CommandBuilder end(Consumer<Boolean> end) {
    this.end = end;
    return this;
  }

  public CommandBuilder end(Runnable func) {
    this.end = ignore -> func.run();
    return this;
  }

  public CommandBuilder addRequirements(Subsystem... subsystems) {
    this.requirements = subsystems;
    return this;
  }

  public Command build() {
    return new FunctionalCommand(init, execute, end, isFinished, requirements);
  }

  public Command build(Subsystem... subsystems) {
    return new FunctionalCommand(init, execute, end, isFinished, subsystems);
  }
}
