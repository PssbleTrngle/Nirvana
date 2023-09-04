package galena.blissful.compat;

import galena.blissful.BlissfulConstants;
import galena.blissful.index.BlissfuItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;

@JeiPlugin
public class BlissfulJeiPlugin implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(BlissfulConstants.MOD_ID, "jei");

    private static final IIngredientSubtypeInterpreter<ItemStack> POTION_INTERPRETER = (ingredient, context) -> {
        var potion = PotionUtils.getPotion(ingredient);
        var effects = PotionUtils.getMobEffects(ingredient);
        var builder = new StringBuilder(potion.getName(""));
        effects.forEach(it -> builder.append(";").append(it));
        return builder.toString();
    };

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(BlissfuItems.POTION_BONG.get(), POTION_INTERPRETER);
    }
}
