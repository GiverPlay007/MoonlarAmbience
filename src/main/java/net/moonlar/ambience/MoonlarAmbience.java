package net.moonlar.ambience;

import net.moonlar.ambience.commands.AmbienceCommand;
import net.moonlar.ambience.listeners.WeatherListener;
import net.moonlar.ambience.daylight.DayLightBehaviour;
import net.moonlar.ambience.daylight.DayLightManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TimeZone;

public final class MoonlarAmbience extends JavaPlugin {

  private boolean weatherEnabled = true;

  private TimeZone timeZone;
  private DayLightManager dayLightManager;
  private DayLightBehaviour behaviour;

  @Override
  public void onEnable() {
    reload();

    getServer().getPluginManager().registerEvents(new WeatherListener(this), this);
    getCommand("ambience").setExecutor(new AmbienceCommand(this));

    dayLightManager = new DayLightManager(this);
    dayLightManager.reset();
  }

  @Override
  public void onDisable() {
    if(dayLightManager != null) {
      dayLightManager.end();
      dayLightManager = null;
    }
  }

  public void reload() {
    dayLightManager.end();

    saveDefaultConfig();
    reloadConfig();

    weatherEnabled = getConfig().getBoolean("Weather.ChangesEnabled");
    timeZone = TimeZone.getTimeZone(getConfig().getString("DayLightCycle.TimeZone"));

    try {
      behaviour = DayLightBehaviour.valueOf(getConfig().getString("DayLightCycle.Behaviour"));
    } catch (IllegalArgumentException e) {
      behaviour = DayLightBehaviour.NORMAL;
      getConfig().set("DayLightCycle.Behaviour", "NORMAL");
      saveConfig();
    }

    dayLightManager.reset();
  }

  public boolean isWeatherEnabled() {
    return weatherEnabled;
  }

  public TimeZone getDayLightTimeZone() {
    return timeZone;
  }

  public DayLightBehaviour getDayLightBehaviour() {
    return behaviour;
  }

  public void sendVersion(CommandSender sender) {
    sender.sendMessage(ChatColor.GREEN + getDescription().getFullName());
  }
}
