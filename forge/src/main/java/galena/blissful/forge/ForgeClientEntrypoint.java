package galena.blissful.forge;

import galena.blissful.BlissfulClient;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ForgeClientEntrypoint {

    public static void init() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener((RegisterColorHandlersEvent.Item event) ->
                BlissfulClient.registerItemColors(event::register)
        );
    }

}
