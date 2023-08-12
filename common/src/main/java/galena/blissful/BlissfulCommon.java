package galena.blissful;

import galena.blissful.index.BlissfulEffects;
import galena.blissful.index.BlissfulBlocks;
import galena.blissful.index.BlissfuItems;

public class BlissfulCommon {

    public static void init() {
        BlissfulBlocks.register();
        BlissfuItems.register();
        BlissfulEffects.register();
    }

}
