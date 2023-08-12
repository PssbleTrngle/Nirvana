package galena.blissful.index;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import galena.blissful.platform.Services;
import galena.blissful.world.item.LazyFoodItem;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;

public class BlissfuItems {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final ItemEntry<Item> HEMP = REGISTRATE
            .item("hemp", Item::new)
            .tab(CreativeModeTabs.INGREDIENTS)
            .register();

    public static final ItemEntry<Item> WEED = REGISTRATE
            .item("weed", Item::new)
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .register();

    private static final NonNullSupplier<FoodProperties> BROWNIES_FOOD = NonNullSupplier.lazy(() -> new FoodProperties.Builder()
            .effect(new MobEffectInstance(BlissfulEffects.PEACE.get(), 100, 0), 1.0F)
            .nutrition(2)
            .saturationMod(0.1F)
            .build()
    );

    public static final ItemEntry<LazyFoodItem> WEED_BROWNIES = REGISTRATE
            .item("weed_brownies", p -> new LazyFoodItem(p, BROWNIES_FOOD))
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .register();

    private static final NonNullSupplier<FoodProperties> JOINT_FOOD = NonNullSupplier.lazy(() -> new FoodProperties.Builder()
            .effect(new MobEffectInstance(BlissfulEffects.PEACE.get(), 100, 0), 1.0F)
            .alwaysEat()
            .build()
    );

    public static final ItemEntry<LazyFoodItem> JOINT = REGISTRATE
            .item("joint", p -> new LazyFoodItem(p, JOINT_FOOD))
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .register();

    public static final ItemEntry<ItemNameBlockItem> HEMP_SEEDS = REGISTRATE
            .item("hemp_seeds", p -> new ItemNameBlockItem(BlissfulBlocks.HEMP.get(), p))
            .tab(CreativeModeTabs.NATURAL_BLOCKS)
            .recipe((c, p) -> p.singleItem(DataIngredient.items(HEMP.get()), RecipeCategory.MISC, c, 1, 2))
            .register();

    public static void register() {
        // loads this class
    }

}
