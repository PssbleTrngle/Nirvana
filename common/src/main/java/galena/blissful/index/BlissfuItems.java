package galena.blissful.index;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import galena.blissful.platform.Services;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;

public class BlissfuItems {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final ItemEntry<Item> HEMP = REGISTRATE
            .item("hemp", Item::new)
            .tab(CreativeModeTabs.NATURAL_BLOCKS)
            .register();

    public static final ItemEntry<Item> WEED = REGISTRATE
            .item("weed", Item::new)
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .register();

    public static final ItemEntry<Item> JOINT = REGISTRATE
            .item("joint", Item::new)
            .tab(CreativeModeTabs.FOOD_AND_DRINKS)
            .register();

    public static final ItemEntry<ItemNameBlockItem> HEMP_SEEDS = REGISTRATE
            .item("hemp_seeds", p -> new ItemNameBlockItem(BlissfulBlocks.HEMP.get(), p))
            .tab(CreativeModeTabs.NATURAL_BLOCKS)
            .recipe((c, p) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, c.getEntry())
                    .requires(HEMP.get())
                    .unlockedBy("has_hemp", RegistrateRecipeProvider.has(c.getEntry()))
                    .save(p))
            .register();

    public static void register() {
        // loads this class
    }

}
