package galena.nirvana.forge.compat;

import galena.nirvana.compat.NirvanaJeiCompat;
import galena.nirvana.index.NirvanaItems;
import galena.nirvana.index.NirvanaRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

@JeiPlugin
public class NirvanaForgeJeiPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (ModList.get().isLoaded("create")) {
            CreateCompat.addJeiRecipes(registration);
        }

        registration.addRecipes(RecipeTypes.CRAFTING, NirvanaRecipeTypes.createSuspiciousRecipes());
    }

    @Override
    public ResourceLocation getPluginUid() {
        return NirvanaJeiCompat.ID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(NirvanaItems.POTION_BONG.get(), NirvanaJeiCompat::interpretPotion);
        registration.registerSubtypeInterpreter(NirvanaItems.HERBAL_SALVE.get(), NirvanaJeiCompat::interpretSuspiciousItem);
        registration.registerSubtypeInterpreter(NirvanaItems.FILLED_PIPE.get(), NirvanaJeiCompat::interpretSuspiciousItem);
    }

}
