package galena.blissful.forge.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

public class JointClientExtensions implements IClientItemExtensions {

    @Override
    public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack stack) {
        return HumanoidModel.ArmPose.SPYGLASS;
    }

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return IClientItemExtensions.super.getCustomRenderer();
    }
}
