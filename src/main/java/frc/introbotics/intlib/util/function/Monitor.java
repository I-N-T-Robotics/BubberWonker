package frc.introbotics.intlib.util.function;

import java.util.function.BiFunction;
import lombok.Getter;

public class Monitor<T> {
  private final BiFunction<T, T, T> func;
  @Getter private T value;

  public Monitor(BiFunction<T, T, T> func, T initialValue) {
    this.func = func;
    this.value = initialValue;
  }

  public void poll(T newValue) {
    value = func.apply(value, newValue);
  }
}
