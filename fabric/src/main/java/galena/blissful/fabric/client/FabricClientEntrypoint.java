package galena.blissful.fabric.client;

import galena.blissful.client.JointModels;
import galena.blissful.index.BlissfulItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

public class FabricClientEntrypoint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BuiltinItemRendererRegistry.INSTANCE.register(BlissfulItems.JOINT.get(), new JointRenderer());
        ModelLoadingPlugin.register(context -> context.addModels(JointModels.FLAT_MODEL, JointModels.HAND_MODEL));
    }

}
