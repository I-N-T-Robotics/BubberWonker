package com.rambots4571.rampage.util.function;

public class Toggle {
  public static Runnable make(Runnable first, Runnable second) {
    return new Runnable() {
      boolean firstRan = false;

      @Override
      public void run() {
        if (firstRan) {
          second.run();
        } else {
          first.run();
        }
        firstRan = !firstRan;
      }
    };
  }
}
