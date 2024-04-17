package galena.nirvana.forge.services;

import galena.nirvana.platform.services.IBrewingRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class ForgeBrewingRegistry implements IBrewingRegistry {

    @Override
    public void addRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
        BrewingRecipeRegistry.addRecipe(input, ingredient, output);
    }

}
