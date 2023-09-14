package galena.blissful.client;

import com.mojang.blaze3d.vertex.PoseStack;
import galena.blissful.BlissfulConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class JointRenderer extends BlockEntityWithoutLevelRenderer {

    private static final ModelResourceLocation HAND_MODEL = new ModelResourceLocation(BlissfulConstants.MOD_ID, "joint_in_hand", "inventory");

    public JointRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
        var renderer = Minecraft.getInstance().getItemRenderer();
        var model = renderer.getItemModelShaper().getModelManager().getModel(HAND_MODEL);

        model.getTransforms().getTransform(transformType).apply(false, pose);
        renderer.
    }

}
