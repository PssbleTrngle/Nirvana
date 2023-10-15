package galena.blissful.fabric.datagen;

import galena.blissful.BlissfulConstants;
import galena.blissful.index.BlissfuItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class AdditionalBlissfulRecipes extends FabricRecipeProvider {

    public AdditionalBlissfulRecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.LEAD)
                .pattern("sh ")
                .pattern("hs ")
                .pattern("  s")
                .define('s', Items.STRING)
                .define('h', BlissfuItems.HEMP.get())
                .unlockedBy("has_hemp", has(BlissfuItems.HEMP))
                .save(exporter, new ResourceLocation(BlissfulConstants.MOD_ID, "lead_from_hemp"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.LEATHER)
                .pattern("hh")
                .pattern("hh")
                .define('h', BlissfuItems.HEMP.get())
                .unlockedBy("has_hemp", has(BlissfuItems.HEMP))
                .save(exporter, new ResourceLocation(BlissfulConstants.MOD_ID, "leather_from_hemp"));
    }

}
