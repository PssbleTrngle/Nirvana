package galena.nirvana;

import galena.nirvana.index.NirvanaBlocks;
import galena.nirvana.index.NirvanaBrewing;
import galena.nirvana.index.NirvanaEffects;
import galena.nirvana.index.NirvanaItems;
import galena.nirvana.index.NirvanaPaintings;
import galena.nirvana.index.NirvanaRecipeTypes;
import galena.nirvana.index.NirvanaSounds;
import galena.nirvana.platform.Services;
import net.minecraft.core.registries.Registries;

public class NirvanaCommon {

    public static void init() {
        NirvanaSounds.register();
        NirvanaEffects.register();
        NirvanaBlocks.register();
        NirvanaItems.register();
        NirvanaRecipeTypes.register();
        NirvanaPaintings.register();

        Services.PLATFORM.getRegistrate().addRegisterCallback(Registries.POTION, NirvanaBrewing::register);
    }

}
