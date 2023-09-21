package galena.blissful.fabric.client;

import galena.blissful.index.BlissfuItems;
import galena.blissful.world.item.JointItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;

public class FabricClientEntrypoint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BuiltinItemRendererRegistry.INSTANCE.register(BlissfuItems.JOINT.get(), new JointRenderer());
        ModelLoadingPlugin.register(context -> context.addModels(JointItem.FLAT_MODEL, JointItem.HAND_MODEL));
    }

}
