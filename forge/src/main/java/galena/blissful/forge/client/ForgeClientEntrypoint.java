package galena.blissful.forge.client;

import galena.blissful.world.item.JointItem;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ForgeClientEntrypoint {

    public static void init() {
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(ForgeClientEntrypoint::registerModels);
    }

    private static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(JointItem.HAND_MODEL);
        event.register(JointItem.FLAT_MODEL);
    }

}
