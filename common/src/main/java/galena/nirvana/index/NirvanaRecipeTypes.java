package galena.nirvana.index;

import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import galena.nirvana.platform.Services;
import galena.nirvana.world.recipe.SuspicousCraftingRecipe;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.FlowerBlock;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class NirvanaRecipeTypes {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final RegistryEntry<? extends RecipeSerializer<?>> SUSPICIOUS_RECIPE_SERIALIZER = REGISTRATE
            .generic("suspicious_crafting", Registries.RECIPE_SERIALIZER, SuspicousCraftingRecipe.Serializer::new)
            .register();

    public static Stream<Pair<ItemLike, ItemStack>> getSuspiciousVariants(ItemLike output) {
        return BuiltInRegistries.ITEM.getTag(ItemTags.SMALL_FLOWERS)
                .stream()
                .flatMap(HolderSet.ListBacked::stream)
                .map(Holder::value)
                .filter(BlockItem.class::isInstance)
                .map(item -> ((BlockItem) item).getBlock())
                .filter(FlowerBlock.class::isInstance)
                .map(FlowerBlock.class::cast)
                .map(flower -> {
                    var outputStack = new ItemStack(output);
                    MobEffect mobeffect = flower.getSuspiciousEffect();
                    SuspiciousStewItem.saveMobEffect(outputStack, mobeffect, flower.getEffectDuration());

                    return new Pair<>(flower, outputStack);
                });
    }

    private static Stream<CraftingRecipe> createSuspiciousRecipes(Ingredient base, ItemLike result, int flowerCount, int weedCount) {
        var group = BuiltInRegistries.ITEM.getKey(result.asItem());
        var weed = Ingredient.of(NirvanaItems.WEED);

        return getSuspiciousVariants(result).<CraftingRecipe>map(pair -> {
            var flowerBlock = pair.getFirst().asItem();
            var output = pair.getSecond();
            var type = BuiltInRegistries.ITEM.getKey(flowerBlock);

            Ingredient flower = Ingredient.of(flowerBlock);
            NonNullList<Ingredient> inputs = NonNullList.createWithCapacity(flowerCount + weedCount + 1);
            for (int i = 0; i < flowerCount; i++) inputs.add(flower);
            for (int i = 0; i < weedCount; i++) inputs.add(weed);
            inputs.add(base);

            ResourceLocation id = group.withSuffix("/" + type.getNamespace() + "/" + type.getPath());
            return new ShapelessRecipe(id, group.toString(), CraftingBookCategory.MISC, output, inputs);
        });
    }

    public static List<CraftingRecipe> createSuspiciousRecipes() {
        return Stream.of(
                createSuspiciousRecipes(Ingredient.of(Items.BOWL), NirvanaItems.HERBAL_SALVE, 3, 3),
                createSuspiciousRecipes(Ingredient.of(NirvanaItems.EMPTY_PIPE), NirvanaItems.FILLED_PIPE, 6, 1)
        ).flatMap(Function.identity()).toList();
    }

    public static void register() {
        // loads this class
    }

}
