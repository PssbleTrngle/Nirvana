package galena.nirvana.world.item;

import com.google.common.collect.ImmutableList;
import galena.nirvana.platform.Services;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.item.UseAnim;

import java.util.List;

public class HerbalSalveItem extends SuspiciousStewItem {

    public HerbalSalveItem(Properties properties) {
        super(properties);
    }

    public static List<MobEffectInstance> getEffects(ItemStack stack) {
        var builder = ImmutableList.<MobEffectInstance>builder();

        CompoundTag compoundTag = stack.getTag();

        if (compoundTag != null && compoundTag.contains("Effects", 9)) {
            ListTag listTag = compoundTag.getList("Effects", 10);

            for (int i = 0; i < listTag.size(); ++i) {
                CompoundTag effectTag = listTag.getCompound(i);
                int duration;
                if (effectTag.contains("EffectDuration", 99)) {
                    duration = effectTag.getInt("EffectDuration");
                } else {
                    duration = Services.CONFIG.common().herbalSalveDuration();
                }

                MobEffect mobEffect = MobEffect.byId(effectTag.getInt("EffectId"));
                if (mobEffect != null) builder.add(new MobEffectInstance(mobEffect, duration));
            }
        }

        return builder.build();
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        var effects = getEffects(stack);

        if (!effects.isEmpty()) {
            effects.forEach(target::addEffect);

            if (!(player.getAbilities().instabuild)) {
                if (stack.getCount() > 1) {
                    stack.shrink(1);
                } else {
                    var remainder = getCraftingRemainingItem();
                    player.setItemInHand(hand, ItemStack.EMPTY);
                    if (remainder != null) player.addItem(remainder.getDefaultInstance());
                }
            }

            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }

        return super.interactLivingEntity(stack, player, target, hand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (!getEffects(stack).isEmpty()) return UseAnim.BLOCK;
        return super.getUseAnimation(stack);
    }
}
