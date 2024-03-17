package me.minignomer.elementsmp.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack energy1Multiplier;
    public static ItemStack energy2Multiplier;
    public static ItemStack energy3Multiplier;

    public static ItemStack reRoll;
    public static ItemStack life;
    public static ItemStack revive;
    public static ItemStack worldBorder;

    public static ItemStack flame;
    public static ItemStack hydro;
    public static ItemStack geo;
    public static ItemStack wind;
    public static ItemStack lightning;
    public static ItemStack luck;

    // Old:
    public static ItemStack oldHydro;
    public static ItemStack oldHydro2;
    public static ItemStack oldGeo;
    public static ItemStack oldWind;

    public static void init() {
        createLife();
        createEnergy1Multiplier();
        createEnergy2Multiplier();
        createEnergy3Multiplier();
        createReRoll();
        createRevive();
        createWorldBorder();
        createFlame();
        createHydro();
        createGeo();
        createWind();
        createLightning();
        createLuck();
        createOldHydro();
        createOldHydro2();
        createOldWind();
        createOldGeo();
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ

    private static void createFlame() {
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§c§lFlame Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₆₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aFlame Boost");
        lore.add("§7Make an explosion to boost yourself");
        lore.add("§7forwards (doesn’t do damage)");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aRing of Fire");
        lore.add("§7Creates rings of fire that damage");
        lore.add("§7nearby players");
        lore.add(" ");
        lore.add("§8₅₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click] §r§aMolotov Fireball");
        lore.add("§7Shoots a fireball that damages other players");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Fire resistance");
        m.setLore(lore);
        m.setCustomModelData(1);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        flame = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createOldHydro() {
        ItemStack item = new ItemStack(Material.BLUE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§9§lHydro Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₈₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aRiptide");
        lore.add("§7Allows you to riptide super far in water");
        lore.add(" ");
        lore.add("§8₂₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aAngelic Cleansing");
        lore.add("§7Remove bad effects from trusted and remove");
        lore.add("§7good effects from not trusted");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click] §r§aHypothermia");
        lore.add("§7Stops the player hit from taking");
        lore.add("§7damage, after 3 seconds it deals");
        lore.add("§7all the damage at once");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Water breathing");
        lore.add("§7 - Dolphin's grace");
        m.setLore(lore);
        m.setCustomModelData(2);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        oldHydro = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createOldHydro2() {
        ItemStack item = new ItemStack(Material.BLUE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§9§lHydro Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₈₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aRiptide");
        lore.add("§7Allows you to riptide super far in water");
        lore.add(" ");
        lore.add("§8₂₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aAngelic Cleansing");
        lore.add("§7Remove bad effects from trusted and remove");
        lore.add("§7good effects from not trusted");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click] §r§aHypothermia");
        lore.add("§7Stops the player hit from taking");
        lore.add("§7damage, after 3 seconds it deals");
        lore.add("§7all the damage at once");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Water breathing");
        m.setLore(lore);
        m.setCustomModelData(2);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        oldHydro2 = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createHydro() {
        ItemStack item = new ItemStack(Material.BLUE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§9§lHydro Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₈₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aRiptide");
        lore.add("§7Allows you to riptide super far in water");
        lore.add(" ");
        lore.add("§8₂₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aAngelic Cleansing");
        lore.add("§7Remove bad effects from yourself and trusted and");
        lore.add("§7remove good effects from not trusted players");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click] §r§aHypothermia");
        lore.add("§7Stops the player hit from taking");
        lore.add("§7damage, after 5 seconds it deals");
        lore.add("§7all the damage at once");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Water breathing");
        lore.add("§7 - Increased swim speed");
        m.setLore(lore);
        m.setCustomModelData(2);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        hydro = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createGeo() {
        ItemStack item = new ItemStack(Material.ORANGE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§6§lGeo Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₂₈₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click Player] §r§aCorrosion");
        lore.add("§7Gets the player stuck in the ground for a few seconds");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aCauterise");
        lore.add("§7Nullifies several melee attacks when activated");
        lore.add(" ");
        lore.add("§8₃₈₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aEarth Turret");
        lore.add("§7Allows you to shoot the block in");
        lore.add("§7your offhand (the harder the");
        lore.add("§7block the more damage it does)");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Haste");
        m.setLore(lore);
        m.setCustomModelData(3);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        geo = item;
    }

    private static void createOldGeo() {
        ItemStack item = new ItemStack(Material.ORANGE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§6§lGeo Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₁₈₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click Player] §r§aCorrosion");
        lore.add("§7Gets the player stuck in the ground for a few seconds");
        lore.add(" ");
        lore.add("§8₂₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aCauterise");
        lore.add("§7Nullifies several melee attacks when activated");
        lore.add(" ");
        lore.add("§8₃₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aEarth Turret");
        lore.add("§7Allows you to shoot the block in");
        lore.add("§7your offhand (the harder the");
        lore.add("§7block the more damage it does)");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Resistance");
        m.setLore(lore);
        m.setCustomModelData(3);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        oldGeo = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createOldWind() {
        ItemStack item = new ItemStack(Material.WHITE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§f§lWind Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift in air] §r§aDouble Jump");
        lore.add("§7Allows you to double jump");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click Player] §r§aSkyrocket");
        lore.add("§7Hit a player to shoot them into the");
        lore.add("§7sky then quickly back down again");
        lore.add(" ");
        lore.add("§8₄₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aTornado");
        lore.add("§7Creates a tornado");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Speed I");
        m.setLore(lore);
        m.setCustomModelData(4);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        oldWind = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createWind() {
        ItemStack item = new ItemStack(Material.WHITE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§f§lWind Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₆₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift in air] §r§aDouble Jump");
        lore.add("§7Allows you to double jump");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click Player] §r§aSkyrocket");
        lore.add("§7Hit a player to shoot them into the");
        lore.add("§7sky then quickly back down again");
        lore.add(" ");
        lore.add("§8₄₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aTornado");
        lore.add("§7Creates a tornado");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Speed I");
        m.setLore(lore);
        m.setCustomModelData(4);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        wind = item;
    }

    private static void createEnergy1Multiplier() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§5§lEnergy Multiplier");
        List<String> lore = new ArrayList<>();
        lore.add("§2§n§lLevel 1: ");
        lore.add(" ");
        lore.add("§aRight click to get energy 2x faster");
        lore.add("§afor 5 minutes!");
        m.setLore(lore);
        m.setCustomModelData(5);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        energy1Multiplier = item;
    }

    private static void createEnergy2Multiplier() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§5§lEnergy Multiplier");
        List<String> lore = new ArrayList<>();
        lore.add("§2§n§lLevel 2: ");
        lore.add(" ");
        lore.add("§aRight click to get energy 3x faster");
        lore.add("§afor 5 minutes!");
        m.setLore(lore);
        m.setCustomModelData(6);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        energy2Multiplier = item;
    }

    private static void createEnergy3Multiplier() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§5§lEnergy Multiplier");
        List<String> lore = new ArrayList<>();
        lore.add("§2§n§lLevel 3: ");
        lore.add(" ");
        lore.add("§aRight click to get energy 4x faster");
        lore.add("§afor 5 minutes!");
        m.setLore(lore);
        m.setCustomModelData(7);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        energy3Multiplier = item;
    }

    private static void createReRoll() {
        ItemStack item = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§4§lE§r§c§ll§r§6§le§r§e§lm§r§2§le§r§a§ln§r§b§lt §r§3§lR§r§1§le§r§9§lR§r§d§lo§r§5§ll§r§f§ll"); // Element ReRoll alternates colours every letter
        List<String> lore = new ArrayList<>();
        lore.add("§6§lGives you a random element!");
        m.setLore(lore);
        m.setCustomModelData(8);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        reRoll = item;
    }

    private static void createRevive() {
        ItemStack item = new ItemStack(Material.CHAIN_COMMAND_BLOCK);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§1§lRevive");
        List<String> lore = new ArrayList<>();
        lore.add("§9§lAllows you to unban a player that");
        lore.add("§9§lhas been eliminated!");
        m.setLore(lore);
        m.setCustomModelData(9);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        revive = item;
    }

    private static void createLife() {
        ItemStack item = new ItemStack(Material.REPEATING_COMMAND_BLOCK);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§4§lLife");
        List<String> lore = new ArrayList<>();
        lore.add("§c§lAllows you to give yourself a life!");
        m.setLore(lore);
        m.setCustomModelData(10);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        life = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createLightning() {
        ItemStack item = new ItemStack(Material.YELLOW_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§e§lLightning Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aThunder Cloud");
        lore.add("§7Creates a rain cloud above nearby");
        lore.add("§7enemies that strikes lightning");
        lore.add(" ");
        lore.add("§8₃₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click player] §r§aAbility Paralysis");
        lore.add("§7Temporarily disables all abilities of");
        lore.add("§7the player hit");
        lore.add(" ");
        lore.add("§8₇₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aLightning Strike");
        lore.add("§7Spawns mass amounts of lightning strikes");
        lore.add("§7within a 30 block radius that stuns players");
        lore.add("§7they hit (you’re immune)");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Haste");
        m.setLore(lore);
        m.setCustomModelData(11);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        lightning = item;
    }

    // ₁₂₃₄₅₆₇₈₉₀ ᴍɪɴᴜᴛᴇ/sᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ
    private static void createLuck() {
        ItemStack item = new ItemStack(Material.BLUE_DYE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§9§lHydro Element");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8₈₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Right-Click] §r§aRiptide");
        lore.add("§7Allows you to riptide super far in water");
        lore.add(" ");
        lore.add("§8₂₅₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Shift Right-Click] §r§aAngelic Cleansing");
        lore.add("§7Remove bad effects from trusted and remove");
        lore.add("§7good effects from not trusted");
        lore.add(" ");
        lore.add("§8₃₀₀ ᴇɴᴇʀɢʏ");
        lore.add("§d[Left-Click] §r§aHypothermia");
        lore.add("§7Stops the player hit from taking");
        lore.add("§7damage, after 3 seconds it deals");
        lore.add("§7all the damage at once");
        lore.add(" ");
        lore.add("§b§lMisc:");
        lore.add("§7 - Water breathing");
        lore.add("§7 - Dolphin's grace");
        m.setLore(lore);
        m.setCustomModelData(12);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        luck = item;
    }

    private static void createWorldBorder() {
        ItemStack item = new ItemStack(Material.STRUCTURE_BLOCK);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§3§lWorld Border Expander");
        List<String> lore = new ArrayList<>();
        lore.add("§bIncreases the world border!");
        lore.add("§c§l(ONE TIME USE)");
        m.setLore(lore);
        m.setCustomModelData(13);
        m.addEnchant(Enchantment.LUCK, 1, true);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(m);

        worldBorder = item;
    }
}