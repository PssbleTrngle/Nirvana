package galena.blissful.world.item;

import galena.blissful.index.BlissfulEffects;
import galena.blissful.world.effects.IStackingEffect;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public abstract class SmokingItem extends Item {

    public SmokingItem(Properties properties) {
        super(properties);
    }

    abstract Stream<MobEffectInstance> getEffects(ItemStack stack, @Nullable Level level, @Nullable LivingEntity entity);

    abstract double getRadius(ItemStack stack, @Nullable Level level, @Nullable LivingEntity entity);

    private void addParticles(ServerLevel level, LivingEntity entity) {
        var rot = entity.getLookAngle().scale(0.6);
        level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                entity.getX() + rot.x, entity.getEyeY() + rot.y, entity.getZ() + rot.z,
                5,
                0.0, 0.2 + entity.getRandom().nextDouble() * 0.1, 0.0,
                0.02
        );
    }

    private boolean isStackingEffect(MobEffect effect) {
        var holder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
        return holder.is(BlissfulEffects.STACKING_EFFECTS);
    }

    private void applyEffect(MobEffectInstance instance, ItemStack source, LivingEntity target, LivingEntity cause) {
        var existing = target.getEffect(instance.getEffect());

        if (existing != null && isStackingEffect(instance.getEffect())) {
            var increased = new MobEffectInstance(
                    instance.getEffect(),
                    instance.getDuration(),
                    existing.getAmplifier() + 1,
                    instance.isAmbient(),
                    instance.isVisible(),
                    instance.showIcon(),
                    null,
                    instance.getFactorData()
            );
            target.addEffect(increased);
            if (instance.getEffect() instanceof IStackingEffect stacking) {
                stacking.onIncreasedTo(increased, source, target, target.level());
            }
        } else if (instance.getEffect().isInstantenous()) {
            instance.getEffect().applyInstantenousEffect(cause, cause, target, instance.getAmplifier(), 1.0);
        } else {
            target.addEffect(instance);
        }
    }

    private void applyEffects(ItemStack source, Level level, LivingEntity user) {
        var effects = getEffects(source, level, user);
        var range = getRadius(source, level, user) * 2;
        var targets = level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(user.position(), range, range, range));

        effects.forEach(effect -> targets.forEach(target ->
                applyEffect(effect, source, target, user)
        ));
    }

    private static ItemStack takeHit(ItemStack stack) {
        var remainder = stack.getItem().getCraftingRemainingItem();

        if (stack.isDamageableItem()) {
            stack.setDamageValue(stack.getDamageValue() + 1);
            if (stack.getDamageValue() == stack.getMaxDamage()) {
                stack.shrink(1);
            }
        } else {
            stack.shrink(1);
        }

        if (stack.isEmpty()) {
            if (remainder != null) return remainder.getDefaultInstance();
        }

        return stack;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        entity.gameEvent(GameEvent.DRINK);

        if (level instanceof ServerLevel serverLevel) {
            applyEffects(stack, level, entity);
            addParticles(serverLevel, entity);
        }

        if (entity instanceof ServerPlayer player) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
        }


        if (entity instanceof Player player) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                var consumed = takeHit(stack);

                if (stack.isEmpty()) {
                    player.getInventory().add(consumed);
                    return consumed;
                }
            }
        }

        return stack;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPYGLASS;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
