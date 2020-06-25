package com.okamos.Daybreak.listeners;

import com.okamos.Daybreak.Daybreak;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class BedListener implements Listener {
  public static final Map<UUID, BukkitTask> Tasks = new HashMap<>();

  @EventHandler
  public void onBedEnter(final PlayerBedEnterEvent event) {
    World world = event.getBed().getWorld();
    BukkitTask task = Bukkit.getScheduler().runTaskTimer(Daybreak.getDaybreak(), new Runnable() {
      @Override
      public void run() {
        if (getSleeping(world).size() == 0) {
          Tasks.get(event.getBed().getWorld().getUID()).cancel();
          return;
        }
        // is night?
        if (world.getTime() > 12950 || world.getTime() < 23950) {
          world.setTime(0);
          if (world.hasStorm()) {
            world.setStorm(false);
          }
          if (world.isThundering()) {
            world.setThundering(false);
          }
        }
      }
    }, 10 * 20L, 10 * 20L);
    Tasks.put(world.getUID(), task);
  }

  @EventHandler
  public void onBedLeave(final PlayerBedLeaveEvent event) {
    World world = event.getBed().getWorld();
    final int sleeping = getSleeping(world).size();
    if (sleeping == 0) {
      Tasks.get(event.getBed().getWorld().getUID()).cancel();
    }
  }

  public static List<Player> getSleeping(final World world) {
    return world.getPlayers().stream().filter(Player::isSleeping).collect(toList());
  }
}
