package galena.nirvana.mixins;

import galena.nirvana.index.NirvanaTags;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Redirect(
            method = "renderArmWithItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/UseAnim;")
    )
    public UseAnim hideFirstPersonJoint(ItemStack instance) {
        var defaultAnimation = instance.getUseAnimation();
        if(!(instance.is(NirvanaTags.SMOKING_ITEM))) return defaultAnimation;
      //  if(player.getUseItem() != instance) return defaultAnimation;

        return UseAnim.BOW;
    }

}
