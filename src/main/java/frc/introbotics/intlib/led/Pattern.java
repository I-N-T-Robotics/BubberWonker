package frc.introbotics.intlib.led;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pattern {
  private final int pulseWidth;
  private final double sparkValue;

  public static final class Rainbow {
    public static final Pattern BASE = new Pattern(1005, -0.99);
    public static final Pattern PARTY = new Pattern(1015, -0.97);
    public static final Pattern OCEAN = new Pattern(1025, -0.95);
    public static final Pattern LAVA = new Pattern(1035, -0.93);
    public static final Pattern FOREST = new Pattern(1045, -0.91);
    public static final Pattern GLITTER = new Pattern(1055, -0.89);
  }

  public static final Pattern CONFETTI = new Pattern(1065, -0.87);

  public static final class Shot {
    public static final Pattern RED = new Pattern(1075, -0.85);
    public static final Pattern BLUE = new Pattern(1085, -0.83);
    public static final Pattern WHITE = new Pattern(1095, -0.81);
    public static final Pattern COLOR_1 = new Pattern(1565, 0.13);
    public static final Pattern COLOR_2 = new Pattern(1665, 0.33);
  }

  public static final class Sinelon {
    public static final Pattern RAINBOW = new Pattern(1105, -0.79);
    public static final Pattern PARTY = new Pattern(1115, -0.77);
    public static final Pattern OCEAN = new Pattern(1125, -0.75);
    public static final Pattern LAVA = new Pattern(1135, -0.73);
    public static final Pattern FOREST = new Pattern(1145, -0.71);
    public static final Pattern COLOR_1_COLOR_2 = new Pattern(1775, 0.55);
  }

  public static final class BeatsPerMinute {
    public static final Pattern RAINBOW = new Pattern(1155, -0.69);
    public static final Pattern PARTY = new Pattern(1165, -0.67);
    public static final Pattern OCEAN = new Pattern(1175, -0.65);
    public static final Pattern LAVA = new Pattern(1185, -0.63);
    public static final Pattern FOREST = new Pattern(1195, -0.61);
    public static final Pattern CUSTOM_COLORS = new Pattern(1715, 0.43);
  }

  public static final class Fire {
    public static final Pattern MEDIUM = new Pattern(1205, -0.59);
    public static final Pattern LARGE = new Pattern(1215, -0.57);
  }

  public static final class Twinkle {
    public static final Pattern RAINBOW = new Pattern(1225, -0.55);
    public static final Pattern PARTY = new Pattern(1235, -0.53);
    public static final Pattern OCEAN = new Pattern(1245, -0.51);
    public static final Pattern LAVA = new Pattern(1255, -0.49);
    public static final Pattern FOREST = new Pattern(1265, -0.47);
    public static final Pattern COLOR_1_COLOR_2 = new Pattern(1755, 0.51);
  }

  public static final class ColorWaves {
    public static final Pattern RAINBOW = new Pattern(1275, -0.45);
    public static final Pattern PARTY = new Pattern(1285, -0.43);
    public static final Pattern OCEAN = new Pattern(1295, -0.41);
    public static final Pattern LAVA = new Pattern(1305, -0.39);
    public static final Pattern FOREST = new Pattern(1315, -0.37);
    public static final Pattern COLOR_1_COLOR_2 = new Pattern(1765, 0.53);
  }

  public static final class LarsonScanner {
    public static final Pattern RED = new Pattern(1325, -0.35);
    public static final Pattern GRAY = new Pattern(1335, -0.33);
    public static final Pattern COLOR_1 = new Pattern(1495, -0.01);
    public static final Pattern COLOR_2 = new Pattern(1595, 0.19);
  }

  public static final class LightChase {
    public static final Pattern RED = new Pattern(1345, -0.31);
    public static final Pattern BLUE = new Pattern(1355, -0.29);
    public static final Pattern GRAY = new Pattern(1365, -0.27);
    public static final Pattern COLOR_1 = new Pattern(1505, 0.01);
    public static final Pattern COLOR_2 = new Pattern(1605, 0.21);
  }

  public static final class HeartBeat {
    public static final Pattern RED = new Pattern(1375, -0.25);
    public static final Pattern BLUE = new Pattern(1385, -0.23);
    public static final Pattern WHITE = new Pattern(1395, -0.21);
    public static final Pattern GRAY = new Pattern(1405, -0.19);

    public static final class Slow {
      public static final Pattern COLOR_1 = new Pattern(1515, 0.03);
      public static final Pattern COLOR_2 = new Pattern(1615, 0.23);
    }

    public static final class Medium {
      public static final Pattern COLOR_1 = new Pattern(1525, 0.05);
      public static final Pattern COLOR_2 = new Pattern(1625, 0.25);
    }

    public static final class Fast {
      public static final Pattern COLOR_1 = new Pattern(1535, 0.07);
      public static final Pattern COLOR_2 = new Pattern(1635, 0.27);
    }
  }

  public static final class Breathe {
    public static final Pattern RED = new Pattern(1415, -0.17);
    public static final Pattern BLUE = new Pattern(1425, -0.15);
    public static final Pattern GRAY = new Pattern(1435, -0.13);

    public static final class Slow {
      public static final Pattern COLOR_1 = new Pattern(1545, 0.09);
      public static final Pattern COLOR_2 = new Pattern(1645, 0.29);
    }

    public static final class Fast {
      public static final Pattern COLOR_1 = new Pattern(1555, 0.11);
      public static final Pattern COLOR_2 = new Pattern(1655, 0.31);
    }
  }

  public static final class Strobe {
    public static final Pattern RED = new Pattern(1445, -0.11);
    public static final Pattern BLUE = new Pattern(1455, -0.09);
    public static final Pattern GOLD = new Pattern(1465, -0.07);
    public static final Pattern WHITE = new Pattern(1475, -0.05);
    public static final Pattern COLOR_1 = new Pattern(1575, 0.15);
    public static final Pattern COLOR_2 = new Pattern(1675, 0.35);
  }

  public static final class EndToEndBlack {
    public static final Pattern COLOR_1 = new Pattern(1485, -0.03);
    public static final Pattern COLOR_2 = new Pattern(1585, 0.17);
  }

  public static final class DualColors {
    public static final class Sparkle {
      public static final Pattern COLOR_1_ON_COLOR_2 = new Pattern(1685, 0.37);
      public static final Pattern COLOR_2_ON_COLOR_1 = new Pattern(1695, 0.39);
    }

    public static final class EndToEndBlend {
      public static final Pattern COLOR_1_TO_COLOR_2 = new Pattern(1725, 0.45);
      public static final Pattern COLOR_2_TO_COLOR_1 = new Pattern(1735, 0.47);
    }

    public static final Pattern GRADIENT = new Pattern(1705, 0.41);
    public static final Pattern COLOR_1_COLOR_2 = new Pattern(1745, 0.49);
  }

  public static final class SolidColor {
    public static final Pattern HOT_PINK = new Pattern(1785, 0.57);
    public static final Pattern DARK_RED = new Pattern(1795, 0.59);
    public static final Pattern RED = new Pattern(1805, 0.61);
    public static final Pattern RED_ORANGE = new Pattern(1815, 0.63);
    public static final Pattern ORANGE = new Pattern(1825, 0.65);
    public static final Pattern GOLD = new Pattern(1835, 0.67);
    public static final Pattern YELLOW = new Pattern(1845, 0.69);
    public static final Pattern LAWN_GREEN = new Pattern(1855, 0.71);
    public static final Pattern LIME = new Pattern(1865, 0.73);
    public static final Pattern DARK_GREEN = new Pattern(1875, 0.75);
    public static final Pattern GREEN = new Pattern(1885, 0.77);
    public static final Pattern BLUE_GREEN = new Pattern(1895, 0.79);
    public static final Pattern AQUA = new Pattern(1905, 0.81);
    public static final Pattern SKY_BLUE = new Pattern(1915, 0.83);
    public static final Pattern DARK_BLUE = new Pattern(1925, 0.85);
    public static final Pattern BLUE = new Pattern(1935, 0.87);
    public static final Pattern BLUE_VIOLET = new Pattern(1945, 0.89);
    public static final Pattern VIOLET = new Pattern(1955, 0.91);
    public static final Pattern WHITE = new Pattern(1965, 0.93);
    public static final Pattern GRAY = new Pattern(1975, 0.95);
    public static final Pattern DARK_GRAY = new Pattern(1985, 0.97);
    public static final Pattern BLACK = new Pattern(1995, 0.99);
  }
}
