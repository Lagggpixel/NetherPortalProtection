package me.lagggpixel.netherportalprotection.commands;

import me.lagggpixel.netherportalprotection.Main;
import me.lagggpixel.netherportalprotection.hooks.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NetherPortalProtectionCommand implements CommandExecutor {

    public NetherPortalProtectionCommand() {
        Objects.requireNonNull(Main.getInstance().getCommand("netherportalprotection")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            Main.getInstance().reloadConfig();
            Main.getInstance().loadConfigValues();
            sender.sendMessage(PlaceholderAPI.parseText((Player) sender, Main.getInstance().getReloadMessage().replace("{time}", String.valueOf(System.currentTimeMillis()))));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getNetherPortalPaddingMessage()));
        }
        return true;
    }
}
