package galena.blissful.fabric.compat;

import galena.blissful.compat.BlissfulJeiCompat;
import galena.blissful.index.BlissfuItems;
import galena.blissful.index.BlissfulRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

public class BlissfulJeiFabricPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(RecipeTypes.CRAFTING, BlissfulRecipeTypes.createSalveRecipes());
    }

    @Override
    public ResourceLocation getPluginUid() {
        return BlissfulJeiCompat.ID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(BlissfuItems.POTION_BONG.get(), BlissfulJeiCompat::interpretPotion);
    }

}
