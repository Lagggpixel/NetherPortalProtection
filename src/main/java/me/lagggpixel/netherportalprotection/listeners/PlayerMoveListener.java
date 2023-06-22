package me.lagggpixel.netherportalprotection.listeners;

import me.lagggpixel.netherportalprotection.Main;
import me.lagggpixel.netherportalprotection.managers.TaskManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerMoveListener implements Listener {

    public PlayerMoveListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        if (to == null) return;
        World world = to.getWorld();
        if (world == null) return;

        if (world.getBlockAt(to).getType() == Material.NETHER_PORTAL) {
            if (Main.getInstance().getTeleportBackEnabled()) {
                if (!player.hasMetadata("portalTimer")) {
                    player.setMetadata("portalTimer", new FixedMetadataValue(Main.getInstance(), System.currentTimeMillis()));
                }
                if (!Main.getInstance().getWarningTasks().containsKey(player)) {
                    TaskManager.startWarningTask(player);
                }
            }
        } else {
            player.removeMetadata("portalTimer", Main.getInstance());
            TaskManager.cancelWarningTask(player);
        }
    }
}

