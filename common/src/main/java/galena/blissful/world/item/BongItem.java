package galena.blissful.world.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class BongItem extends SmokingItem{

    public BongItem(Properties properties) {
        super(properties);
    }

    @Override
    Stream<MobEffectInstance> getEffects(ItemStack stack, Level level, LivingEntity entity) {
        return PotionUtils.getMobEffects(stack).stream();
    }

}
