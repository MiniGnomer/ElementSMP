package me.minignomer.elementsmp;

import com.samjakob.spigui.SpiGUI;
import me.minignomer.elementsmp.commands.*;
import me.minignomer.elementsmp.display.DisplayActionBar;
import me.minignomer.elementsmp.effects.Effects;
import me.minignomer.elementsmp.element.Element;
import me.minignomer.elementsmp.element.ReRoll;
import me.minignomer.elementsmp.energy.Energy;
import me.minignomer.elementsmp.flame.RingOfFire;
import me.minignomer.elementsmp.geo.Cauterize;
import me.minignomer.elementsmp.flame.FlameBoost;
import me.minignomer.elementsmp.flame.ShootFireball;
import me.minignomer.elementsmp.geo.Corrosion;
import me.minignomer.elementsmp.geo.EarthTurret;
import me.minignomer.elementsmp.hydro.AngelicCleansing;
import me.minignomer.elementsmp.hydro.Hypothermia;
import me.minignomer.elementsmp.hydro.IncreaseSwimSpeed;
import me.minignomer.elementsmp.hydro.Riptide;
import me.minignomer.elementsmp.items.ItemManager;
import me.minignomer.elementsmp.items.ItemRecipies;
import me.minignomer.elementsmp.items.NotDroppable;
import me.minignomer.elementsmp.items.UpdateElementItems;
import me.minignomer.elementsmp.lightning.AbilityParalysis;
import me.minignomer.elementsmp.lightning.ThunderCloud;
import me.minignomer.elementsmp.lightning.ThunderStrike;
import me.minignomer.elementsmp.lives.LifeItem;
import me.minignomer.elementsmp.lives.Lives;
import me.minignomer.elementsmp.lives.Revive;
import me.minignomer.elementsmp.misc.ModifyEvents;
import me.minignomer.elementsmp.wind.Tornado;
import me.minignomer.elementsmp.wind.DoubleJump;
import me.minignomer.elementsmp.wind.SkyRocket;
import me.minignomer.elementsmp.worldborderexpander.ExpandWorldBorderItem;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElementSMP extends JavaPlugin {

    public static ElementSMP plugin;

    public static SpiGUI spiGUI;

    @Override
    public void onEnable() {
        plugin = this;
        spiGUI = new SpiGUI(this);

        loadItems();
        listeners();
        loadCommands();

        ModifyEvents.beds.add(Material.WHITE_BED);
        ModifyEvents.beds.add(Material.LIGHT_GRAY_BED);
        ModifyEvents.beds.add(Material.GRAY_BED);
        ModifyEvents.beds.add(Material.BLACK_BED);
        ModifyEvents.beds.add(Material.BROWN_BED);
        ModifyEvents.beds.add(Material.RED_BED);
        ModifyEvents.beds.add(Material.ORANGE_BED);
        ModifyEvents.beds.add(Material.YELLOW_BED);
        ModifyEvents.beds.add(Material.LIME_BED);
        ModifyEvents.beds.add(Material.GREEN_BED);
        ModifyEvents.beds.add(Material.CYAN_BED);
        ModifyEvents.beds.add(Material.LIGHT_BLUE_BED);
        ModifyEvents.beds.add(Material.BLUE_BED);
        ModifyEvents.beds.add(Material.PURPLE_BED);
        ModifyEvents.beds.add(Material.MAGENTA_BED);
        ModifyEvents.beds.add(Material.PINK_BED);

    }

    public void loadItems() {
        ItemManager.init();
        ItemRecipies.init();
    }

    public void listeners() {
        // General
        getServer().getPluginManager().registerEvents(new Element(), this);
        getServer().getPluginManager().registerEvents(new Effects(), this);
        getServer().getPluginManager().registerEvents(new NotDroppable(), this);
        getServer().getPluginManager().registerEvents(new Lives(), this);
        getServer().getPluginManager().registerEvents(new DisplayActionBar(), this);
        getServer().getPluginManager().registerEvents(new Energy(), this);
        getServer().getPluginManager().registerEvents(new ReRoll(), this);
        getServer().getPluginManager().registerEvents(new LifeItem(), this);
        getServer().getPluginManager().registerEvents(new Revive(), this);
        getServer().getPluginManager().registerEvents(new UpdateElementItems(), this);
        getServer().getPluginManager().registerEvents(new ExpandWorldBorderItem(), this);

        // Flame
        getServer().getPluginManager().registerEvents(new FlameBoost(), this);
        getServer().getPluginManager().registerEvents(new RingOfFire(), this);
        getServer().getPluginManager().registerEvents(new ShootFireball(), this);
        // Hydro
        getServer().getPluginManager().registerEvents(new Riptide(), this);
        getServer().getPluginManager().registerEvents(new AngelicCleansing(), this);
        getServer().getPluginManager().registerEvents(new Hypothermia(), this);
        getServer().getPluginManager().registerEvents(new IncreaseSwimSpeed(), this);
        // Wind
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        getServer().getPluginManager().registerEvents(new SkyRocket(), this);
        getServer().getPluginManager().registerEvents(new Tornado(), this);
        // Geo
        getServer().getPluginManager().registerEvents(new Corrosion(), this);
        getServer().getPluginManager().registerEvents(new Cauterize(), this);
        getServer().getPluginManager().registerEvents(new EarthTurret(), this);
        // Lightning
        getServer().getPluginManager().registerEvents(new ThunderCloud(), this);
        getServer().getPluginManager().registerEvents(new AbilityParalysis(), this);
        getServer().getPluginManager().registerEvents(new ThunderStrike(), this);

        // Misc
        getServer().getPluginManager().registerEvents(new ModifyEvents(), this);
    }

    public void loadCommands() {
        getCommand("element").setExecutor(new ElementCommand());
        getCommand("element").setTabCompleter(new ElementTabCompleter());
        getCommand("trust").setExecutor(new TrustCommand());
        getCommand("trust").setTabCompleter(new TrustTabCompleter());
        getCommand("lives").setExecutor(new LivesCommand());
        getCommand("lives").setTabCompleter(new LivesTabCompleter());
        getCommand("energy").setExecutor(new EnergyCommand());
        getCommand("energy").setTabCompleter(new EnergyTabCompleter());
        getCommand("get").setExecutor(new GetCommand());
        getCommand("get").setTabCompleter(new GetTabCompleter());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand());
    }
}
