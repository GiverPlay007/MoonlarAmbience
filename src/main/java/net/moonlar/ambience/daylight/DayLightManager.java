package net.moonlar.ambience.daylight;

import net.moonlar.ambience.MoonlarAmbience;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;

import java.util.Calendar;

public class DayLightManager implements Runnable {

  public static final short DAY_TICKS = 24000;
  public static final short MIDNIGHT = 18000;;

  private final MoonlarAmbience plugin;
  private final Server server;

  private BukkitTask task;

  private int ticks;

  public DayLightManager(MoonlarAmbience plugin) {
    this.plugin = plugin;
    this.server = plugin.getServer();
  }

  @Override
  public void run() {
    ++ticks;

    if(ticks >= DAY_TICKS) {
      ticks = 0;
    }

    server.getWorlds().forEach(w -> w.setTime(ticks));
  }

  public void reset() {
    end();

    DayLightBehaviour behaviour = plugin.getDayLightBehaviour();

    for (World world : server.getWorlds()) {
      DayLightCycleHelper.setRule(world, behaviour != DayLightBehaviour.NORMAL);
      world.setTime(behaviour.getTicks());
    }

    if(behaviour != DayLightBehaviour.REALTIME) {
      return;
    }

    Calendar calendar = Calendar.getInstance(plugin.getDayLightTimeZone());

    int seconds = calendar.get(Calendar.HOUR_OF_DAY) * 3600;
    seconds += calendar.get(Calendar.MINUTE) * 60;
    seconds += calendar.get(Calendar.SECOND);

    ticks = MIDNIGHT + (int) (seconds / 3.6) - DAY_TICKS;
    task = server.getScheduler().runTaskTimer(plugin, this, 0L, 72);
  }

  public void end() {
    if(task != null) {
      task.cancel();
    }

    task = null;
    ticks = MIDNIGHT;
  }
}
