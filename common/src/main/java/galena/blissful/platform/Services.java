package galena.blissful.platform;

import galena.blissful.platform.services.IBrewingRegistry;
import galena.blissful.platform.services.IDataGenHelper;
import galena.blissful.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IDataGenHelper DATAGEN = load(IDataGenHelper.class);
    public static final IBrewingRegistry BREWING = load(IBrewingRegistry.class);

    private static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }

}
