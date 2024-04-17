package galena.nirvana.world.effects;

import galena.nirvana.index.NirvanaTags;
import galena.nirvana.platform.Services;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PeaceEffect extends MobEffect implements IStackingEffect {
    public PeaceEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xaabf4b);
    }

    @Override
    public void onIncreasedTo(MobEffectInstance instance, ItemStack source, LivingEntity target, Level level) {
        var hitsTaken = instance.getAmplifier() + 1;
        if (source.is(NirvanaTags.NAUSEATING) && hitsTaken >= Services.CONFIG.common().nauseaAfterHits()) {
            target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 20, 0));
        }
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int i) {
        super.addAttributeModifiers(entity, attributes, i);
        if(entity instanceof Mob mob) {
            mob.setTarget(null);
        }
    }

}
