package com.okamos.Daybreak.listeners;

import com.okamos.Daybreak.Daybreak;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BedListener implements Listener {
  public static final Map<UUID, BukkitTask> Tasks = new HashMap<>();

  @EventHandler
  public void onBedEnter(final PlayerBedEnterEvent event) {
    BukkitTask task = Bukkit.getScheduler().runTaskLater(Daybreak.getDaybreak(), new Runnable() {
      @Override
      public void run() {
        World world = event.getBed().getWorld();
        // is night?
        if (world.getTime() > 12950 || world.getTime() < 23950) {
          world.setTime(0);
        }
      }
    }, 10 * 20L);
    Tasks.put(event.getPlayer().getUniqueId(), task);
  }

  @EventHandler
  public void onBedLeave(final PlayerBedLeaveEvent event) {
    Tasks.get(event.getPlayer().getUniqueId()).cancel();
  }
}
