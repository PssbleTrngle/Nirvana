package galena.blissful.fabric;

import com.tterrag.registrate.Registrate;
import galena.blissful.BlissfulCommon;
import galena.blissful.BlissfulConstants;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {

    public static final Registrate REGISTRATE = Registrate.create(BlissfulConstants.MOD_ID);

    public static void init() {
        REGISTRATE.register();
        BlissfulCommon.init();
    }

    @Override
    public void onInitialize() {
        init();
    }

}
