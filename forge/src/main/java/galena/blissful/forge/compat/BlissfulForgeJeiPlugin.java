package galena.blissful.forge.compat;

import galena.blissful.compat.BlissfulJeiCompat;
import galena.blissful.index.BlissfuItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
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
