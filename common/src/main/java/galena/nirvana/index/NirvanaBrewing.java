package galena.nirvana.index;

import galena.nirvana.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class NirvanaBrewing {

    private static ItemStack withPotion(ItemLike item, Potion potion) {
        return PotionUtils.setPotion(new ItemStack(item), potion);
    }

    private static void registerMix(ItemStack ingredient, ItemStack from) {
        var input = PotionUtils.getPotion(from) == Potions.WATER ? NirvanaItems.BONG.asStack() : from;
        var output = PotionBrewing.mix(ingredient, from);
        if (output == from) return;
        Services.BREWING.addRecipe(Ingredient.of(input), Ingredient.of(ingredient), output);
    }

    private static void registerBongRecipes() {
        var waterBottle = withPotion(Items.POTION, Potions.WATER);

        Services.BREWING.addRecipe(Ingredient.of(waterBottle), Ingredient.of(NirvanaItems.WEED), NirvanaItems.BONG.asStack());

        var catalysts = BuiltInRegistries.ITEM.stream()
                .map(ItemStack::new)
                .filter(PotionBrewing::isIngredient)
                .toList();

        BuiltInRegistries.POTION.stream().forEach(potion -> {
            var from = withPotion(NirvanaItems.POTION_BONG, potion);
            var potionStack = withPotion(Items.POTION, potion);
            catalysts.stream()
                    .filter(it -> PotionBrewing.hasMix(potionStack, it))
                    .forEach(catalyst -> registerMix(catalyst, from));
        });
    }

    public static void register() {
        registerBongRecipes();
    }

}
