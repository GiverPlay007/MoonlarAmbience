package net.moonlar.ambience;

import net.moonlar.ambience.commands.AmbienceCommand;
import net.moonlar.ambience.listeners.WeatherListener;
import net.moonlar.ambience.tasks.DayLightTask;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoonlarAmbience extends JavaPlugin {

  private boolean weatherEnabled = true;
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
    saveDefaultConfig();
    reloadConfig();

    weatherEnabled = getConfig().getBoolean("Weather.ChangesEnabled");
    task.reset();
  }

  public boolean isWeatherEnabled() {
    return weatherEnabled;
  }

  public void sendVersion(CommandSender sender) {
    sender.sendMessage(ChatColor.GREEN + getDescription().getFullName());
  }
}
