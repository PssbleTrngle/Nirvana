package galena.blissful.mixins;

import galena.blissful.index.BlissfulEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(cancellable = true, at = @At("HEAD"), method = "canAttack(Lnet/minecraft/world/entity/LivingEntity;)Z")
    public void canAttack(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        @SuppressWarnings("DataFlowIssue")
        var self = (LivingEntity) (Object) (this);

        if(self.hasEffect(BlissfulEffects.PEACE.get())) {
            cir.setReturnValue(false);
        }
    }

}
