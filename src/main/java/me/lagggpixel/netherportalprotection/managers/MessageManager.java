package me.lagggpixel.netherportalprotection.managers;

import me.lagggpixel.netherportalprotection.Main;
import me.lagggpixel.netherportalprotection.hooks.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class MessageManager {

    public static void sendWarningMessage(Player player, int remainingTime) {
        for (String message : Main.getInstance().getWarningMessages()) {
            String[] messageParts = message.split(" ", 2);
            String prefix = messageParts[0];
            String content = messageParts[1];

            switch (prefix.toLowerCase()) {
                case "[action-bar]":
                    sendActionBarMessage(player, content.replace("{time}", String.valueOf(remainingTime)));
                    break;
                case "[chat]":
                    player.sendMessage(PlaceholderAPI.parseText(player, content.replace("{time}", String.valueOf(remainingTime))));
                    break;
                case "[bossbar]":
                    sendBossBarMessage(player, content.replace("{time}", String.valueOf(remainingTime)));
                    break;
                default:
                    break;
            }
        }
    }
    private static void sendActionBarMessage(Player player, String message) {
        TextComponent text = new TextComponent(PlaceholderAPI.parseText(player, message));
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
    }

    private static void sendBossBarMessage(Player player, String message) {
        BossBar bossBar = Bukkit.createBossBar(PlaceholderAPI.parseText(player, message), BarColor.YELLOW, BarStyle.SOLID);
        bossBar.addPlayer(player);
        bossBar.setVisible(true);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            bossBar.setVisible(false);
            bossBar.removePlayer(player);
        }, 20L * 5);
    }

}

