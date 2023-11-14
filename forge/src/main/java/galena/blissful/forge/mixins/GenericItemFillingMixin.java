package galena.blissful.forge.mixins;

import com.simibubi.create.AllFluids;
import com.simibubi.create.content.fluids.potion.PotionFluid;
import com.simibubi.create.content.fluids.potion.PotionFluidHandler;
import com.simibubi.create.content.fluids.transfer.GenericItemFilling;
import com.simibubi.create.foundation.utility.NBTHelper;
import galena.blissful.index.BlissfulItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GenericItemFilling.class, remap = false)
public class GenericItemFillingMixin {

    @Unique
    private static boolean blissful$isValidPotion(FluidStack fluid) {
        return fluid.getFluid().isSame(AllFluids.POTION.get())
                && NBTHelper.readEnum(fluid.getOrCreateTag(), "Bottle", PotionFluid.BottleType.class) == PotionFluid.BottleType.REGULAR;
    }

    @Inject(require = 0, cancellable = true, at = @At("HEAD"), method = "canItemBeFilled(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;)Z")
    private static void canBongBeFilled(Level world, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (BlissfulItems.BONG.isIn(stack)) cir.setReturnValue(true);
    }

    @Inject(require = 0, cancellable = true, at = @At("HEAD"), method = "getRequiredAmountForItem(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraftforge/fluids/FluidStack;)I")
    private static void getRequiredAmountForBong(Level world, ItemStack stack, FluidStack availableFluid, CallbackInfoReturnable<Integer> cir) {
        if (BlissfulItems.BONG.isIn(stack) && blissful$isValidPotion(availableFluid)) {
            cir.setReturnValue(PotionFluidHandler.getRequiredAmountForFilledBottle(stack, availableFluid));
        }
    }

    @Inject(require = 0, cancellable = true, at = @At("HEAD"), method = "fillItem(Lnet/minecraft/world/level/Level;ILnet/minecraft/world/item/ItemStack;Lnet/minecraftforge/fluids/FluidStack;)Lnet/minecraft/world/item/ItemStack;")
    private static void fillBong(Level world, int requiredAmount, ItemStack stack, FluidStack availableFluid, CallbackInfoReturnable<ItemStack> cir) {
        if (BlissfulItems.BONG.isIn(stack) && blissful$isValidPotion(availableFluid)) {
            var tag = availableFluid.getOrCreateTag();
            var bongStack = BlissfulItems.POTION_BONG.asStack();
            PotionUtils.setPotion(bongStack, PotionUtils.getPotion(tag));
            PotionUtils.setCustomEffects(bongStack, PotionUtils.getCustomEffects(tag));
            stack.shrink(1);
            cir.setReturnValue(bongStack);
        }
    }

}
