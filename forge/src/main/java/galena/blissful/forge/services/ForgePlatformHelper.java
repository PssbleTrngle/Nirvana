package galena.blissful.forge.services;

import com.tterrag.registrate.AbstractRegistrate;
import galena.blissful.forge.ForgeEntrypoint;
import galena.blissful.platform.services.IPlatformHelper;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public AbstractRegistrate<?> getRegistrate() {
        return ForgeEntrypoint.REGISTRATE.get();
    }

}
