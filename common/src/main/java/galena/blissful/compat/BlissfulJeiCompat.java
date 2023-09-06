package galena.blissful.compat;

import galena.blissful.BlissfulConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;

public class BlissfulJeiCompat {

    public static final ResourceLocation ID = new ResourceLocation(BlissfulConstants.MOD_ID, "jei");

    public static String interpretPotion(ItemStack ingredient, Object unused) {
        var potion = PotionUtils.getPotion(ingredient);
        var effects = PotionUtils.getMobEffects(ingredient);
        var builder = new StringBuilder(potion.getName(""));
        effects.forEach(it -> builder.append(";").append(it));
        return builder.toString();
    }
}
