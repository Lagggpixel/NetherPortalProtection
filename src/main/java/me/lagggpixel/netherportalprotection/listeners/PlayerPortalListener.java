package me.lagggpixel.netherportalprotection.listeners;

import me.lagggpixel.netherportalprotection.Main;
import me.lagggpixel.netherportalprotection.managers.TaskManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerPortalListener implements Listener {

    private final BlockFace[] adjacentFaces = {
            BlockFace.NORTH,
            BlockFace.SOUTH,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.UP,
            BlockFace.DOWN
    };

    public PlayerPortalListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerPortal(PlayerPortalEvent event) {

        Player player = event.getPlayer();

        if (event.getTo() == null) {
            throw new IllegalArgumentException("Player teleported into a null location. Please contact the plugin creator.");
        }
        if (event.getTo().getWorld() == null) {
            throw new IllegalArgumentException("Player teleported into a null world. Please contact the plugin creator.");
        }

        if (Main.getInstance().getPaddingEnabled()) {
            clearSurroundingPortalBlocks(event.getTo().getBlock());
        }

        Main.getInstance().getLastPortalLocation().remove(player);
        Main.getInstance().getLastPortalLocation().put(player, event.getFrom());

        if (Main.getInstance().getTeleportBackEnabled()) {
            if (!player.hasMetadata("portalTimer")) {
                player.setMetadata("portalTimer", new FixedMetadataValue(Main.getInstance(), System.currentTimeMillis()));
            }
            if (!Main.getInstance().getWarningTasks().containsKey(player)) {
                TaskManager.startWarningTask(player);
            }
        }
    }

    private void clearSurroundingPortalBlocks(Block portalBlock) {
        if (portalBlock.getType() != Material.NETHER_PORTAL) {
            return;
        }

        BlockData blockData = portalBlock.getBlockData();
        Rotatable rotatable = (Rotatable) blockData;
        BlockFace blockFace = rotatable.getRotation();

        Block clearedBlock1 = portalBlock.getRelative(blockFace);
        clearedBlock1.setType(Material.AIR);

        Block clearedBlock2 = portalBlock.getRelative(blockFace.getOppositeFace());
        clearedBlock2.setType(Material.AIR);

        for (BlockFace face : adjacentFaces) {
            Block adjacentBlock = portalBlock.getRelative(face);
            if (adjacentBlock.getType().equals(Material.NETHER_PORTAL)) {
                clearSurroundingPortalBlocks(adjacentBlock);
            }
        }
    }
}
