package galena.nirvana.fabric.mixins;

import galena.nirvana.fabric.services.FabricBrewingRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionBrewing.class)
public class PotionBrewingMixin {

    @Inject(cancellable = true, at = @At("HEAD"), method = "isIngredient(Lnet/minecraft/world/item/ItemStack;)Z")
    private static void isIngredient(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (FabricBrewingRegistry.isCustomIngredient(stack)) cir.setReturnValue(true);
    }

    @Inject(cancellable = true, at = @At("HEAD"), method = "hasMix(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z")
    private static void hasMix(ItemStack input, ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        if (FabricBrewingRegistry.hasCustomRecipe(input, ingredient)) cir.setReturnValue(true);
    }

    @Inject(cancellable = true, at = @At("HEAD"), method = "mix(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;")
    private static void mix(ItemStack ingredient, ItemStack input, CallbackInfoReturnable<ItemStack> cir) {
        FabricBrewingRegistry.getCustomRecipe(input, ingredient).ifPresent(recipe -> {
            cir.setReturnValue(recipe.output());
        });
    }

}
