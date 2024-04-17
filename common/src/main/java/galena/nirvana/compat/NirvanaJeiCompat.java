package galena.nirvana.compat;

import galena.nirvana.NirvanaConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;

public class NirvanaJeiCompat {

    public static final ResourceLocation ID = new ResourceLocation(NirvanaConstants.MOD_ID, "jei");

    public static String interpretPotion(ItemStack ingredient, Object unused) {
        var potion = PotionUtils.getPotion(ingredient);
        var effects = PotionUtils.getMobEffects(ingredient);
        var builder = new StringBuilder(potion.getName(""));
        effects.forEach(it -> builder.append(";").append(it));
        return builder.toString();
    }
}
