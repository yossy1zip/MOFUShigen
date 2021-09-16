package com.yiorno.mofushigen;

import org.bukkit.plugin.java.JavaPlugin;

public final class MOFUShigen extends JavaPlugin {

    public static MOFUShigen instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new Event(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
