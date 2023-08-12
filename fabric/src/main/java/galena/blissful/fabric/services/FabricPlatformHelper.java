package galena.blissful.fabric.services;

import com.tterrag.registrate.AbstractRegistrate;
import galena.blissful.fabric.FabricEntrypoint;
import galena.blissful.platform.services.IPlatformHelper;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public AbstractRegistrate<?> getRegistrate() {
        return FabricEntrypoint.REGISTRATE;
    }

}
