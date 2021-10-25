package net.moonlar.ambience.tasks;

public enum DayLightBehaviour {
  NORMAL(12000),
  DAY(6000),
  NIGHT(18000),
  REALTIME(0);

  private final int ticks;

  DayLightBehaviour(int ticks) {
    this.ticks = ticks;
  }

  public int getTicks() {
    return ticks;
  }
}
