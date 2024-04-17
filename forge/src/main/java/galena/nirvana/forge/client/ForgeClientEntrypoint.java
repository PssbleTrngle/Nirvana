package galena.nirvana.forge.client;

import galena.nirvana.client.JointModels;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ForgeClientEntrypoint {

    public static void init() {
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(ForgeClientEntrypoint::registerModels);
    }

    private static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(JointModels.HAND_MODEL);
        event.register(JointModels.FLAT_MODEL);
    }

}
