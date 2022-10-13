package com.rambots4571.rampage.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Function {
  @SafeVarargs
  public static <T> List<T> applyAll(Consumer<T> apply, T... t) {
    List<T> list = Arrays.asList(t);
    list.forEach(apply);
    return list;
  }

  @SafeVarargs
  public static <T> List<T> combine(List<T>... lists) {
    List<T> list = new ArrayList<>();
    Arrays.asList(lists).forEach(list::addAll);
    return list;
  }
}
