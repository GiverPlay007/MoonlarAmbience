package net.moonlar.ambience;

import net.moonlar.ambience.listeners.WeatherListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoonlarAmbience extends JavaPlugin {

  private boolean weatherEnabled = true;

  @Override
  public void onEnable() {
    reload();
    getServer().getPluginManager().registerEvents(new WeatherListener(this), this);
  }

  @Override
  public void onDisable() {

  }

  public void reload() {
    saveDefaultConfig();
    reloadConfig();

    weatherEnabled = getConfig().getBoolean("Weather.ChangesEnabled");
  }

  public boolean isWeatherEnabled() {
    return weatherEnabled;
  }
}
