package galena.blissful.forge.compat;

import galena.blissful.compat.BlissfulJeiCompat;
import galena.blissful.index.BlissfulItems;
import galena.blissful.index.BlissfulRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

@JeiPlugin
public class BlissfulForgeJeiPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (ModList.get().isLoaded("create")) {
            CreateCompat.addJeiRecipes(registration);
        }

        registration.addRecipes(RecipeTypes.CRAFTING, BlissfulRecipeTypes.createSalveRecipes());
    }

    @Override
    public ResourceLocation getPluginUid() {
        return BlissfulJeiCompat.ID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(BlissfulItems.POTION_BONG.get(), BlissfulJeiCompat::interpretPotion);
    }

}
