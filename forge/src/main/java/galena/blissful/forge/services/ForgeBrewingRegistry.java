package galena.blissful.forge.services;

import galena.blissful.index.BlissfuItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class ForgeBrewingRegistry {

    public static void addRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
        BrewingRecipeRegistry.addRecipe(input, ingredient, output);
    }

    public static void registerBongRecipe(Ingredient ingredient, Potion from, Potion to) {
        var fromStack = BlissfuItems.BONG.asStack();
        PotionUtils.setPotion(fromStack, from);

        var toStack = BlissfuItems.BONG.asStack();
        PotionUtils.setPotion(toStack, to);

        addRecipe(Ingredient.of(fromStack), ingredient, toStack);
    }

    public static void registerBongRecipes() {
        registerBongRecipe(Ingredient.of(BlissfuItems.WEED), Potions.EMPTY, Potions.HEALING);
    }

}
