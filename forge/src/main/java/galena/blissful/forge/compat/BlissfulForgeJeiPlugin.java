package galena.blissful.forge.compat;

import galena.blissful.compat.BlissfulJeiPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraftforge.fml.ModList;

@JeiPlugin
public class BlissfulForgeJeiPlugin extends BlissfulJeiPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if(ModList.get().isLoaded("create")) {
            CreateCompat.addJeiRecipes(registration);
        }
    }

}
