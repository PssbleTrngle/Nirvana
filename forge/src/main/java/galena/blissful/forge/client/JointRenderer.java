package galena.blissful.forge.client;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import galena.blissful.world.item.JointItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class JointRenderer extends BlockEntityWithoutLevelRenderer {

    public JointRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext mode, PoseStack pose, MultiBufferSource vertexConsumers, int light, int overlay) {
        pose.pushPose();
        Lighting.setupForFlatItems();
        var renderer = Minecraft.getInstance().getItemRenderer();
        var model = renderer.getItemModelShaper().getModelManager().getModel(JointItem.getModel(mode));

        model.getTransforms().getTransform(mode).apply(false, pose);
        RenderType renderType = ItemBlockRenderTypes.getRenderType(stack, false);
        var vertex = ItemRenderer.getFoilBufferDirect(vertexConsumers, renderType, true, stack.hasFoil());
        renderer.renderModelLists(model, stack, light, overlay, pose, vertex);
        pose.popPose();
    }

}
