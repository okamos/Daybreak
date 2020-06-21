package com.okamos.Daybreak;

import org.bukkit.plugin.java.JavaPlugin;

import com.okamos.Daybreak.listeners.PlayerListener;
import com.okamos.Daybreak.listeners.BedListener;

public class Daybreak extends JavaPlugin
{
  private static Daybreak daybreak;

  @Override
  public void onEnable() {
    daybreak = this;
    getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    getServer().getPluginManager().registerEvents(new BedListener(), this);
  }

  @Override
  public void onDisable() {
  }

  public static Daybreak getDaybreak() {
    return daybreak;
  }
}
