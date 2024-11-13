package galena.nirvana.world.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static net.minecraft.world.item.SuspiciousStewItem.EFFECTS_TAG;
import static net.minecraft.world.item.SuspiciousStewItem.EFFECT_DURATION_TAG;
import static net.minecraft.world.item.SuspiciousStewItem.EFFECT_ID_TAG;

public class SuspiciousItem {

    public static List<MobEffectInstance> getEffects(ItemStack stack, int defaultDuration) {
        var builder = ImmutableList.<MobEffectInstance>builder();

        CompoundTag compoundTag = stack.getTag();

        if (compoundTag != null && compoundTag.contains(EFFECTS_TAG, 9)) {
            ListTag listTag = compoundTag.getList(EFFECTS_TAG, 10);

            for (int i = 0; i < listTag.size(); ++i) {
                CompoundTag effectTag = listTag.getCompound(i);
                int duration;
                if (effectTag.contains(EFFECT_DURATION_TAG, 99)) {
                    duration = effectTag.getInt(EFFECT_DURATION_TAG);
                } else {
                    duration = defaultDuration;
                }

                MobEffect mobEffect = MobEffect.byId(effectTag.getInt(EFFECT_ID_TAG));
                if (mobEffect != null) builder.add(new MobEffectInstance(mobEffect, duration));
            }
        }

        return builder.build();
    }

    private SuspiciousItem() {
    }

}
