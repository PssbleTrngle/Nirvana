package galena.blissful.mixins;

import galena.blissful.index.BlissfulEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Redirect(method = "isInvulnerableTo", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/Entity;invulnerable:Z", opcode = 180))
    public boolean canAttack(Entity instance, DamageSource damageSource) {
        return (instance instanceof LivingEntity
                && damageSource.getEntity() instanceof LivingEntity attacker
                && attacker.hasEffect(BlissfulEffects.PEACE.get())
        ) || instance.isInvulnerable();
    }

}
