package me.lagggpixel.netherportalprotection.managers;

import me.lagggpixel.netherportalprotection.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TaskManager {

    public static void startWarningTask(Player player) {
        BukkitTask warningTask;
        warningTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (player.hasMetadata("portalTimer")) {
                    long portalTime = player.getMetadata("portalTimer").get(0).asLong();
                    long elapsedTime = (System.currentTimeMillis() - portalTime) / 1000;
                    int remainingTime = (int) (Main.getInstance().getMaxPortalTime() - elapsedTime);

                    if (Main.getInstance().getWarningTimes().contains(remainingTime)) {
                        MessageManager.sendWarningMessage(player, remainingTime);
                    }

                    if (remainingTime <= 0) {
                        TeleportationManager.teleportPlayer(player);
                        cancelWarningTask(player);
                        player.removeMetadata("portalTimer", Main.getInstance());
                    }

                } else {
                    cancelWarningTask(player);
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);

        Main.getInstance().getWarningTasks().put(player, warningTask);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> cancelWarningTask(player), Main.getInstance().getMaxPortalTime() * 20L);
    }

    public static void cancelWarningTask(Player player) {
        if (Main.getInstance().getWarningTasks().containsKey(player)) {
            Main.getInstance().getWarningTasks().get(player).cancel();
            Main.getInstance().getWarningTasks().remove(player);
        }
    }

}
