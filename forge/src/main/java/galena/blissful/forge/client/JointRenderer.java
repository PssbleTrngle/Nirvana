package galena.blissful.forge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import galena.blissful.client.JointModels;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class JointRenderer extends BlockEntityWithoutLevelRenderer {

    public JointRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext mode, PoseStack pose, MultiBufferSource vertexConsumers, int light, int overlay) {
        JointModels.render(stack, mode, pose, vertexConsumers, ((renderer, model, vertexConsumer) ->
                renderer.renderModelLists(model, stack, light, overlay, pose, vertexConsumer))
        );
    }

}
