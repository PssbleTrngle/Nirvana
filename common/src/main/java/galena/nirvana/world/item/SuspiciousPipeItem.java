package galena.nirvana.world.item;

import galena.nirvana.index.NirvanaItems;
import galena.nirvana.platform.Services;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class SuspiciousPipeItem extends SuspiciousStewItem {

    public SuspiciousPipeItem(Properties properties) {
        super(properties);
    }

    private static List<MobEffectInstance> getEffects(ItemStack stack) {
        return SuspiciousItem.getEffects(stack, Services.CONFIG.common().suspiciousPipeDuration());
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (!getEffects(stack).isEmpty()) return UseAnim.SPYGLASS;
        return super.getUseAnimation(stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        if (getEffects(stack).isEmpty()) return InteractionResultHolder.pass(stack);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 32;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        getEffects(stack).forEach(user::addEffect);
        return user instanceof Player && ((Player) user).getAbilities().instabuild
                ? stack
                : new ItemStack(NirvanaItems.EMPTY_PIPE);
    }

}
