package net.moonlar.ambience;

import net.moonlar.ambience.listeners.WeatherListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoonlarAmbience extends JavaPlugin {

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(new WeatherListener(), this);
  }

  @Override
  public void onDisable() {

  }
}
