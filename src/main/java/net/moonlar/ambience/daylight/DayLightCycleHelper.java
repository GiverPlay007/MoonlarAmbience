package net.moonlar.ambience.daylight;

import org.bukkit.GameRule;
import org.bukkit.World;

public final class DayLightCycleHelper {
  private DayLightCycleHelper() { }

  public static void setRule(World world, Boolean value) {
    try {
      world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, value);
    } catch (NoClassDefFoundError e) {
      world.setGameRuleValue("doDaylightCycle", value.toString());
    }
  }
}
