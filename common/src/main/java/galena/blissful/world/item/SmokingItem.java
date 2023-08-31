package galena.blissful.world.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public abstract class SmokingItem extends Item {

    public SmokingItem(Properties properties) {
        super(properties);
    }

    abstract Stream<MobEffectInstance> getEffects(ItemStack stack, Level level, LivingEntity entity);

    @Override
    public boolean isEdible() {
        return true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        var effect = getEffects(stack, level, entity);

        effect.forEach(entity::addEffect);

        return super.finishUsingItem(stack, level, entity);
    }
}
