package galena.nirvana.fabric.mixins;

import galena.nirvana.fabric.services.FabricBrewingRegistry;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandMenu.PotionSlot.class)
public class PotionSlotMixin {

    @Inject(cancellable = true, at = @At("HEAD"), method = "mayPlaceItem(Lnet/minecraft/world/item/ItemStack;)Z")
    private static void isIngredient(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(FabricBrewingRegistry.isCustomInput(stack)) cir.setReturnValue(true);
    }

}
