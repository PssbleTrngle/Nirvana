package galena.nirvana.fabric.client;

import galena.nirvana.client.JointModels;
import galena.nirvana.index.NirvanaItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

public class FabricClientEntrypoint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BuiltinItemRendererRegistry.INSTANCE.register(NirvanaItems.JOINT.get(), new JointRenderer());
        ModelLoadingPlugin.register(context -> context.addModels(JointModels.FLAT_MODEL, JointModels.HAND_MODEL));
    }

}
