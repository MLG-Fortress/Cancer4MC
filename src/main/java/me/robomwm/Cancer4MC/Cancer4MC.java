package me.robomwm.Cancer4MC;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by RoboMWM on 12/9/2016.
 */
public class Cancer4MC extends JavaPlugin implements Listener
{
    FileConfiguration config = getConfig();

    public void onEnable()
    {
        config.addDefault("adMessage", "&bPlease wait 5 seconds because we are a cancer server that really wants you to give us money to bypass this wait.");
        config.addDefault("wait", 5);
        config.options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    void onInventoryOpen(final InventoryOpenEvent event)
    {
        if (event.getPlayer().hasPermission("cancer.has"))
            return;

        final Inventory inventory = event.getInventory();
        event.setCancelled(true);
        event.getPlayer().sendMessage(config.getString("adMessage"));

        new BukkitRunnable()
        {
            public void run()
            {
                event.getPlayer().openInventory(inventory);
            }
        }.runTaskLater(this, config.getInt("wait"));
    }
}
