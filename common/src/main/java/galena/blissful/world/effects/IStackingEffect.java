package galena.blissful.world.effects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IStackingEffect {

    void onIncreasedTo(MobEffectInstance instance, ItemStack source, LivingEntity target, Level level);

}
