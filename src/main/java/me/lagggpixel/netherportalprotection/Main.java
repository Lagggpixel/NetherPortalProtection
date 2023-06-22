package me.lagggpixel.netherportalprotection;

import lombok.Getter;
import me.lagggpixel.netherportalprotection.commands.NetherPortalProtectionCommand;
import me.lagggpixel.netherportalprotection.listeners.PlayerMoveListener;
import me.lagggpixel.netherportalprotection.listeners.PlayerPortalListener;
import me.lagggpixel.netherportalprotection.listeners.PlayerQuitListener;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Getter
public class Main extends JavaPlugin {

    private static Main INSTANCE;

    private final HashMap<@NotNull Player, @NotNull Location> lastPortalLocation = new HashMap<>();

    private String reloadMessage;
    private String netherPortalPaddingMessage;

    private Boolean paddingEnabled;
    private Boolean teleportBackEnabled;

    private int maxPortalTime;
    private List<Integer> warningTimes;
    private List<String> warningMessages;
    private List<String> commands;
    private final HashMap<Player, BukkitTask> warningTasks = new HashMap<>();

    @Override
    public void onEnable() {

        INSTANCE = this;

        loadConfigValues();
        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        new PlayerMoveListener();
        new PlayerPortalListener();
        new PlayerQuitListener();
    }

    private void registerCommands() {
        new NetherPortalProtectionCommand();
    }

    public void loadConfigValues() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        paddingEnabled = getConfig().getBoolean("padding");
        maxPortalTime = getConfig().getInt("delay");
        warningTimes = getConfig().getIntegerList("warnings.times");
        warningMessages = getConfig().getStringList("warnings.messages");
        reloadMessage = Objects.requireNonNull(getConfig().getString("lang.reload"));
        netherPortalPaddingMessage = Objects.requireNonNull(getConfig().getString("lang.correct-use"));
        teleportBackEnabled = (maxPortalTime != -1);
        commands =getConfig().getStringList("commands");
    }


    public static Main getInstance() {
        return INSTANCE;
    }
}
