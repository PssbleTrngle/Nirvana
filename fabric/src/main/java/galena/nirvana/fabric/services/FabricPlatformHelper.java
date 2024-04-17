package galena.nirvana.fabric.services;

import com.tterrag.registrate.AbstractRegistrate;
import galena.nirvana.fabric.FabricEntrypoint;
import galena.nirvana.platform.services.IPlatformHelper;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public AbstractRegistrate<?> getRegistrate() {
        return FabricEntrypoint.REGISTRATE;
    }

}
