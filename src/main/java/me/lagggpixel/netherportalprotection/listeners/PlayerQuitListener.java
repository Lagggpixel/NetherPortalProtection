package me.lagggpixel.netherportalprotection.listeners;

import me.lagggpixel.netherportalprotection.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    public PlayerQuitListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Main.getInstance().getLastPortalLocation().remove(player);
    }
}
