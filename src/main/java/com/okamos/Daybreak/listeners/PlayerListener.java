package com.okamos.Daybreak.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
  @EventHandler
  public void onPlayerJoin(final PlayerJoinEvent event) {
    event.getPlayer().sendMessage(
       "Daybreak: " + ChatColor.DARK_PURPLE + "Deep sleep breaks the night(10seconds)"
    );
  }
}
