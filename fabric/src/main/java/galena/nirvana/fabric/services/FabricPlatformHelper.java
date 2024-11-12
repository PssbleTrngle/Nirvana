package galena.nirvana.fabric.services;

import galena.nirvana.fabric.FabricEntrypoint;
import galena.nirvana.platform.registrate.NirvanaRegistrate;
import galena.nirvana.platform.services.IPlatformHelper;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public NirvanaRegistrate<?> getRegistrate() {
        return FabricEntrypoint.REGISTRATE;
    }

}
