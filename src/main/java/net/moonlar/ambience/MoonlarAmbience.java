package net.moonlar.ambience;

import net.moonlar.ambience.commands.AmbienceCommand;
import net.moonlar.ambience.listeners.WeatherListener;
import net.moonlar.ambience.tasks.DayLightTask;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TimeZone;

public final class MoonlarAmbience extends JavaPlugin {

  private boolean weatherEnabled = true;
  private TimeZone timeZone;
  private DayLightTask task;

  @Override
  public void onEnable() {
    reload();

    getServer().getPluginManager().registerEvents(new WeatherListener(this), this);
    getCommand("ambience").setExecutor(new AmbienceCommand(this));

    task = new DayLightTask(this);
    task.reset();
  }

  @Override
  public void onDisable() {
    if(task != null) {
      task.end();
      task = null;
    }
  }

  public void reload() {
    task.end();

    saveDefaultConfig();
    reloadConfig();

    weatherEnabled = getConfig().getBoolean("Weather.ChangesEnabled");
    timeZone = TimeZone.getTimeZone(getConfig().getString("DayLightCycle.TimeZone"));
    task.reset();
  }

  public boolean isWeatherEnabled() {
    return weatherEnabled;
  }

  public TimeZone getDayLightTimeZone() {
    return timeZone;
  }

  public void sendVersion(CommandSender sender) {
    sender.sendMessage(ChatColor.GREEN + getDescription().getFullName());
  }
}
