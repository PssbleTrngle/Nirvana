package galena.blissful.world.item;

import galena.blissful.index.BlissfulEffects;
import galena.blissful.platform.Services;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class JointItem extends SmokingItem {

    public JointItem(Properties properties) {
        super(properties);
    }

    @Override
    Stream<MobEffectInstance> getEffects(ItemStack stack, @Nullable Level level, @Nullable LivingEntity entity) {
        return Stream.of(new MobEffectInstance(BlissfulEffects.PEACE.get(), 20 * Services.CONFIG.common().jointPeaceSeconds(), 0));
    }

}
