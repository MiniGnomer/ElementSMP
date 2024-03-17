package me.minignomer.elementsmp.items;

import me.minignomer.elementsmp.ElementSMP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class ItemRecipies {

    public static void init() {
        createReRollRecipe();
        createLifeRecipe();
        createReviveRecipe();
        createMultiplier1Recipe();
        createMultiplier2Recipe();
        createMultiplier3Recipe();
    }

    private static void createReRollRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(ElementSMP.plugin, "reroll"), ItemManager.reRoll);
        recipe.shape(
                "abc",
                "bdb",
                "cba");
        recipe.setIngredient('a', Material.ECHO_SHARD);
        recipe.setIngredient('b', Material.NETHERITE_SCRAP);
        recipe.setIngredient('c', Material.AMETHYST_SHARD);
        recipe.setIngredient('d', Material.CALIBRATED_SCULK_SENSOR);

        Bukkit.addRecipe(recipe);
    }

    private static void createReviveRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(ElementSMP.plugin, "revive"), ItemManager.revive);
        recipe.shape(
                "aba",
                "bcb",
                "aba");
        recipe.setIngredient('a', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('b', Material.END_CRYSTAL);
        recipe.setIngredient('c', Material.ELYTRA);

        Bukkit.addRecipe(recipe);
    }

    private static void createMultiplier1Recipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(ElementSMP.plugin, "multiplierone"), ItemManager.energy1Multiplier);
        recipe.shape(
                "aba",
                "bcb",
                "aba");
        recipe.setIngredient('a', Material.SCULK);
        recipe.setIngredient('b', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('c', Material.DIAMOND_BLOCK);

        Bukkit.addRecipe(recipe);
    }

    private static void createMultiplier2Recipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(ElementSMP.plugin, "multipliertwo"), ItemManager.energy2Multiplier);
        recipe.shape(
                "aba",
                "cdc",
                "aca");
        recipe.setIngredient('a', Material.BLAZE_ROD);
        recipe.setIngredient('b', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('c', Material.NETHERITE_SCRAP);
        recipe.setIngredient('d', new RecipeChoice.ExactChoice(ItemManager.energy1Multiplier));

        Bukkit.addRecipe(recipe);
    }

    private static void createMultiplier3Recipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(ElementSMP.plugin, "multiplierthree"), ItemManager.energy3Multiplier);
        recipe.shape(
                "aba",
                "bcb",
                "aba");
        recipe.setIngredient('a', Material.SHULKER_SHELL);
        recipe.setIngredient('b', Material.END_ROD);
        recipe.setIngredient('c', new RecipeChoice.ExactChoice(ItemManager.energy2Multiplier));

        Bukkit.addRecipe(recipe);
    }

    private static void createLifeRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(ElementSMP.plugin, "life"), ItemManager.life);
        recipe.shape(
                "aba",
                "cdc",
                "aba");
        recipe.setIngredient('a', Material.DIAMOND_BLOCK);
        recipe.setIngredient('b', Material.NETHERITE_INGOT);
        recipe.setIngredient('c', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('d', Material.NETHER_STAR);

        Bukkit.addRecipe(recipe);
    }
}
