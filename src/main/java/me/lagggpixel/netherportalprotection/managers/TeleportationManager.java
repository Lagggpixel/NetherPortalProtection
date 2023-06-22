package me.lagggpixel.netherportalprotection.managers;

import me.lagggpixel.netherportalprotection.Main;
import me.lagggpixel.netherportalprotection.hooks.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportationManager {
    public static void teleportPlayer(Player player) {
        if (Main.getInstance().getLastPortalLocation().containsKey(player)) {
            player.teleport(Main.getInstance().getLastPortalLocation().get(player));
        }
        else {
            for (String command : Main.getInstance().getCommands()) {

                String[] messageParts = command.split(" ", 2);
                String prefix = messageParts[0];
                String content = messageParts[1];

                switch (prefix.toLowerCase()) {
                    case "[player]":
                        player.performCommand(PlaceholderAPI.parseText(player, content));
                        break;
                    case "[console]":
                        Main.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.parseText(player, content));
                        break;
                    default:
                        break;
                }

            }
        }

    }
}
