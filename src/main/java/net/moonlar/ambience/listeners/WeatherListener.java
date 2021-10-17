package net.moonlar.ambience.listeners;

import net.moonlar.ambience.MoonlarAmbience;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

  private final MoonlarAmbience plugin;

  public WeatherListener(MoonlarAmbience plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onWeatherChange(WeatherChangeEvent e) {
    if(!plugin.isWeatherEnabled()) {
      e.setCancelled(true);
    }
  }
}
