package me.lagggpixel.netherportalprotection.hooks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderAPI {

    public static String parseText(Player player, String text) {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return ChatColor.translateAlternateColorCodes('&', me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, text));

        }
        else {
            return ChatColor.translateAlternateColorCodes('&', text);
        }

    }
}
