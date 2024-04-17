package galena.nirvana.fabric.datagen;

import galena.nirvana.NirvanaConstants;
import galena.nirvana.index.NirvanaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class AdditionalNirvanaRecipes extends FabricRecipeProvider {

    public AdditionalNirvanaRecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.LEAD)
                .pattern("sh ")
                .pattern("hs ")
                .pattern("  s")
                .define('s', Items.STRING)
                .define('h', NirvanaItems.HEMP.get())
                .unlockedBy("has_hemp", has(NirvanaItems.HEMP))
                .save(exporter, new ResourceLocation(NirvanaConstants.MOD_ID, "lead_from_hemp"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.LEATHER)
                .pattern("hh")
                .pattern("hh")
                .define('h', NirvanaItems.HEMP.get())
                .unlockedBy("has_hemp", has(NirvanaItems.HEMP))
                .save(exporter, new ResourceLocation(NirvanaConstants.MOD_ID, "leather_from_hemp"));
    }

}
