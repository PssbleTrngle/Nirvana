package galena.blissful.world.effects;

import galena.blissful.index.BlissfuItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PeaceEffect extends MobEffect implements IStackingEffect {
    public PeaceEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xaabf4b);
    }

    @Override
    public void onIncreasedTo(MobEffectInstance instance, ItemStack source, LivingEntity target, Level level) {
        if (source.is(BlissfuItems.NAUSEATING) && instance.getAmplifier() > 9) {
            target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 5, 0));
        }
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int i) {
        super.addAttributeModifiers(entity, attributes, i);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int i) {
        super.removeAttributeModifiers(entity, attributes, i);
    }

}
