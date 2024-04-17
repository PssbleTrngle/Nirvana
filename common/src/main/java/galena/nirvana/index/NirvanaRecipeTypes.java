package galena.nirvana.index;

import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import galena.nirvana.NirvanaConstants;
import galena.nirvana.platform.Services;
import galena.nirvana.world.recipe.HerbalSalveRecipe;
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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.FlowerBlock;

import java.util.List;
import java.util.stream.Stream;

public class NirvanaRecipeTypes {

    public static class SimpleRecipeType<T extends Recipe<?>> implements RecipeType<T> {
    }

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    //public static final RegistryEntry<RecipeType<?>> HERBAL_SALVE_RECIPE_TYPE = REGISTRATE
    //        .generic("herbal_salve_crafting", Registries.RECIPE_TYPE, SimpleRecipeType::new)
    //        .register();

    public static final RegistryEntry<SimpleCraftingRecipeSerializer<HerbalSalveRecipe>> HERBAL_SALVE_RECIPE_SERIALIZER = REGISTRATE
            .generic("herbal_salve_crafting", Registries.RECIPE_SERIALIZER, () -> new SimpleCraftingRecipeSerializer<>(HerbalSalveRecipe::new))
            .register();

    public static Stream<Pair<ItemLike, ItemStack>> getSalves() {
        return BuiltInRegistries.ITEM.getTag(ItemTags.SMALL_FLOWERS)
                .stream()
                .flatMap(HolderSet.ListBacked::stream)
                .map(Holder::value)
                .filter(BlockItem.class::isInstance)
                .map(item -> ((BlockItem) item).getBlock())
                .filter(FlowerBlock.class::isInstance)
                .map(FlowerBlock.class::cast)
                .map(flower -> {
                    ItemStack output = NirvanaItems.HERBAL_SALVE.asStack();
                    MobEffect mobeffect = flower.getSuspiciousEffect();
                    SuspiciousStewItem.saveMobEffect(output, mobeffect, flower.getEffectDuration());

                    return new Pair<>(flower, output);
                });
    }

    public static List<CraftingRecipe> createSalveRecipes() {
        String group = "jei.herbal_salves";
        Ingredient bowl = Ingredient.of(Items.BOWL);
        Ingredient weed = Ingredient.of(NirvanaItems.WEED);

        return getSalves().<CraftingRecipe>map(pair -> {
                    var flowerBlock = pair.getFirst().asItem();
                    var output = pair.getSecond();

                    Ingredient flower = Ingredient.of(flowerBlock);
                    NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, flower, flower, flower, weed, weed, weed, bowl);
                    ResourceLocation id = new ResourceLocation(NirvanaConstants.MOD_ID, group + flowerBlock.getDescriptionId());
                    return new ShapelessRecipe(id, group, CraftingBookCategory.MISC, output, inputs);
                })
                .toList();
    }

    public static void register() {
        // loads this class
    }

}
