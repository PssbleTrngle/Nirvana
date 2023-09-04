package galena.blissful.platform.services;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public interface IBrewingRegistry {

    void addRecipe(Ingredient input, Ingredient ingredient, ItemStack output);

}
