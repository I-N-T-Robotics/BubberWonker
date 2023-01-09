package com.rambots4571.rampage.math;

import edu.wpi.first.math.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleSupplier;

public class Converter {

  public static enum Unit {
    RAW,
    METERS,
    INCHES,
    RADIANS,
    METERS_PER_SEC,
    RADIANS_PER_SEC,
    RPM;
  }

  public static interface Conversion {
    double apply(double value);
  }

  private DoubleSupplier baseValue;
  // Pair: 1 - converts raw to unit
  // Pair: 2 - converts unit to raw
  private Map<Unit, Pair<Conversion, Conversion>> converterFuncs;

  public Converter(DoubleSupplier baseValue) {
    this.baseValue = baseValue;
    converterFuncs = new HashMap<>(6);
  }

  public void addConversion(Unit unit, Conversion rawToUnit, Conversion unitToRaw) {
    converterFuncs.put(unit, new Pair<Conversion, Conversion>(rawToUnit, unitToRaw));
  }

  public double get(Unit unit) {
    if (converterFuncs.containsKey(unit))
      return converterFuncs.get(unit).getFirst().apply(baseValue.getAsDouble());
    return Integer.MAX_VALUE;
  }

  public double convertToBase(Unit unit, double value) {
    if (converterFuncs.containsKey(unit)) return converterFuncs.get(unit).getSecond().apply(value);
    return Integer.MAX_VALUE;
  }
}
