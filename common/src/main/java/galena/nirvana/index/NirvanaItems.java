package galena.nirvana.index;

import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.CreativeModeTabModifier;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import galena.nirvana.NirvanaClient;
import galena.nirvana.platform.Services;
import galena.nirvana.world.item.BongItem;
import galena.nirvana.world.item.HerbalSalveItem;
import galena.nirvana.world.item.JointItem;
import galena.nirvana.world.item.LazyFoodItem;
import galena.nirvana.world.item.ModdedRecordItem;
import galena.nirvana.world.item.PotionBongItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

import java.util.function.Consumer;

public class NirvanaItems {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final ItemEntry<Item> HEMP = REGISTRATE
            .item("hemp", Item::new)
            .tab(CreativeModeTabs.INGREDIENTS)
            .register();

    public static final ItemEntry<ItemNameBlockItem> HEMP_SEEDS = REGISTRATE
            .item("hemp_seeds", p -> new ItemNameBlockItem(NirvanaBlocks.HEMP.get(), p))
            .tab(CreativeModeTabs.NATURAL_BLOCKS)
            .recipe((c, p) -> p.singleItem(DataIngredient.items(HEMP.get()), RecipeCategory.MISC, c, 1, 2))
            .register();

    public static final ItemEntry<Item> WEED = REGISTRATE
            .item("weed", Item::new)
            .lang("Weed Bud")
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .recipe((c, p) -> {
                p.smelting(DataIngredient.items(HEMP.get()), RecipeCategory.MISC, c, 0.25F);
                p.smoking(DataIngredient.items(HEMP.get()), RecipeCategory.MISC, c, 0.25F);
                p.campfire(DataIngredient.items(HEMP.get()), RecipeCategory.MISC, c, 0.25F);
            })
            .register();

    private static final NonNullSupplier<FoodProperties> BROWNIE_FOOD = NonNullSupplier.lazy(() -> new FoodProperties.Builder()
            .effect(new MobEffectInstance(NirvanaEffects.PEACE.get(), 20 * Services.CONFIG.common().browniesPeaceSeconds(), 0), 1.0F)
            .nutrition(2)
            .saturationMod(0.1F)
            .build()
    );

    public static final ItemEntry<LazyFoodItem> WEED_BROWNIE = REGISTRATE
            .item("weed_brownie", p -> new LazyFoodItem(p, BROWNIE_FOOD))
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .recipe((c, p) -> ShapelessRecipeBuilder
                    .shapeless(RecipeCategory.FOOD, c.get(), 2)
                    .requires(HEMP_SEEDS)
                    .requires(Items.WHEAT)
                    .requires(Items.COCOA_BEANS)
                    .unlockedBy("has_hemp_seed", RegistrateRecipeProvider.has(HEMP_SEEDS))
                    .save(p)
            )
            .register();

    public static final ItemEntry<BongItem> BONG = REGISTRATE
            .item("bong", BongItem::new)
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .properties(it -> it.durability(Services.CONFIG.common().getBongHits()))
            .properties(it -> it.craftRemainder(Items.GLASS_BOTTLE))
            .register();

    private static <T extends Item> Consumer<CreativeModeTabModifier> addPotionStacks(ItemBuilder<T, ?> item) {
        return modifier -> BuiltInRegistries.POTION.stream()
                .filter(it -> it != Potions.EMPTY && it != Potions.WATER)
                .map(it -> PotionUtils.setPotion(new ItemStack(item.getEntry()), it))
                .forEach(modifier::accept);
    }

    private static <T extends Item> Consumer<CreativeModeTabModifier> addSalveStacks() {
        return modifier -> NirvanaRecipeTypes.getSalves()
                .map(Pair::getSecond)
                .forEach(modifier::accept);
    }

    public static final ItemEntry<PotionBongItem> POTION_BONG = REGISTRATE
            .item("potion_bong", PotionBongItem::new)
            .transform(it -> it.tab(CreativeModeTabs.FOOD_AND_DRINKS, NirvanaItems.addPotionStacks(it)))
            .color(() -> () -> NirvanaClient.POTION_COLOR)
            .properties(it -> it.durability(Services.CONFIG.common().getBongHits()))
            .properties(it -> it.craftRemainder(Items.GLASS_BOTTLE))
            .model((c, p) -> p.generated(c, p.modLoc("item/bong_potion"), p.modLoc("item/bong_potion_overlay")))
            .register();
    public static final ItemEntry<JointItem> JOINT = REGISTRATE
            .item("joint", Services.PLATFORM::createJointItem)
            .properties(it -> it.durability(Services.CONFIG.common().getJointHits()))
            .tag(NirvanaTags.NAUSEATING)
            .tag(NirvanaTags.ATTACHED_TO_HEAD)
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .model(Services.DATAGEN::joint)
            .recipe((c, p) -> ShapelessRecipeBuilder
                    .shapeless(RecipeCategory.FOOD, c.get())
                    .requires(Items.PAPER)
                    .requires(WEED)
                    .unlockedBy("has_weed", RegistrateRecipeProvider.has(WEED))
                    .save(p)
            )
            .register();

    public static final ItemEntry<HerbalSalveItem> HERBAL_SALVE = REGISTRATE
            .item("herbal_salve", HerbalSalveItem::new)
            .properties(it -> it.stacksTo(1))
            .properties(it -> it.craftRemainder(Items.BOWL))
            .tab(CreativeModeTabs.FOOD_AND_DRINKS, NirvanaItems.addSalveStacks())
            .register();

    public static final ItemEntry<? extends RecordItem> DISC_JAM = REGISTRATE
            .item("music_disc_jam", props -> new ModdedRecordItem(13, NirvanaSounds.JAM, props, 150))
            .properties(it -> it.stacksTo(1))
            .properties(it -> it.rarity(Rarity.RARE))
            .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
            .tag(ItemTags.MUSIC_DISCS)
            .setData(ProviderType.LANG, (context, provider) -> {
                provider.add(context.get(), "Music Disc");
                provider.add(context.get().getDescriptionId() + ".desc", "Jam - firch");
            })
            .register();

    public static void register() {
        // loads this class
    }

}
