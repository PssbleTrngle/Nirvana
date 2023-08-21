package galena.blissful.fabric;

import com.tterrag.registrate.Registrate;
import galena.blissful.BlissfulCommon;
import galena.blissful.BlissfulConstants;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {

    public static final Registrate REGISTRATE = Registrate.create(BlissfulConstants.MOD_ID);

    @Override
    public void onInitialize() {
        BlissfulCommon.init();
        REGISTRATE.register();
    }

}
