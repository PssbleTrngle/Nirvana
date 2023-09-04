package galena.blissful;

import galena.blissful.index.BlissfulBrewing;
import galena.blissful.index.BlissfulEffects;
import galena.blissful.index.BlissfulBlocks;
import galena.blissful.index.BlissfuItems;
import galena.blissful.platform.Services;
import net.minecraft.core.registries.Registries;

public class BlissfulCommon {

    public static void init() {
        BlissfulEffects.register();
        BlissfulBlocks.register();
        BlissfuItems.register();

        Services.PLATFORM.getRegistrate().addRegisterCallback(Registries.POTION, BlissfulBrewing::register);
    }

}
