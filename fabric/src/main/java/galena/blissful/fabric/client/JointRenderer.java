package galena.blissful.fabric.client;

import com.mojang.blaze3d.vertex.PoseStack;
import galena.blissful.world.item.JointItem;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class JointRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    @Override
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack pose, MultiBufferSource vertexConsumers, int light, int overlay) {
        JointItem.render(stack, mode, pose, vertexConsumers, ((renderer, model, vertexConsumer) ->
                renderer.renderModelLists(model, stack, light, overlay, pose, vertexConsumer))
        );
    }

}
