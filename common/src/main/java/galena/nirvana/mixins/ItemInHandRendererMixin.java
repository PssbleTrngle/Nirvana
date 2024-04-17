package galena.nirvana.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import galena.nirvana.world.item.SmokingItem;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(
            method = "renderArmWithItem",
            at = @At("HEAD"),
            cancellable = true
    )
    public void hideFirstPersonJoint(AbstractClientPlayer player, float f, float g, InteractionHand hand, float h, ItemStack stack, float i, PoseStack pose, MultiBufferSource multiBufferSource, int j, CallbackInfo ci) {
        if(!(stack.getItem() instanceof SmokingItem)) return;
        if(player.getUseItem() != stack) return;

        ci.cancel();
    }

}
