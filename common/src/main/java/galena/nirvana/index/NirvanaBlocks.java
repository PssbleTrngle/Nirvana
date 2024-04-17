package galena.nirvana.index;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import galena.nirvana.platform.Services;
import galena.nirvana.world.block.CrateBlock;
import galena.nirvana.world.block.HempCropBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;

public class NirvanaBlocks {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final BlockEntry<HempCropBlock> HEMP = REGISTRATE
            .block("hemp", HempCropBlock::new)
            .initialProperties(() -> Blocks.WHEAT)
            .addLayer(() -> RenderType::cutout)
            .blockstate(Services.DATAGEN::hempCrop)
            .tag(BlockTags.CROPS)
            .tag(NirvanaTags.SUMMER_CROPS)
            .loot(Services.DATAGEN::hempCrop)
            .register();

    public static final BlockEntry<CrateBlock> HEMP_CRATE = REGISTRATE
            .block("hemp_crate", CrateBlock::new)
            .initialProperties(() -> Blocks.BARREL)
            .blockstate(Services.DATAGEN::crate)
            .recipe((c, p) -> p.storage(NirvanaItems.HEMP, RecipeCategory.DECORATIONS, c))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item()
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build()
            .register();

    public static final BlockEntry<CrateBlock> WEED_CRATE = REGISTRATE
            .block("weed_crate", CrateBlock::new)
            .initialProperties(() -> Blocks.BARREL)
            .blockstate(Services.DATAGEN::crate)
            .recipe((c, p) -> p.storage(NirvanaItems.WEED, RecipeCategory.DECORATIONS, c))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item()
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build()
            .register();

    public static final BlockEntry<DoublePlantBlock> BLISS_BLOOM = REGISTRATE
            .block("bliss_bloom", DoublePlantBlock::new)
            .initialProperties(() -> Blocks.ROSE_BUSH)
            .addLayer(() -> RenderType::cutout)
            .blockstate(Services.DATAGEN::blissBloom)
            .loot(Services.DATAGEN::blissBloom)
            .tag(BlockTags.TALL_FLOWERS)
            .item()
            .tab(CreativeModeTabs.NATURAL_BLOCKS)
            .model((c, p) -> p.generated(c, p.modLoc("block/" + c.getName() + "_upper")))
            .build()
            .register();

    public static final BlockEntry<BushBlock> FERAL_HEMP = REGISTRATE
            .block("feral_hemp", BushBlock::new)
            .initialProperties(() -> Blocks.FERN)
            .addLayer(() -> RenderType::cutout)
            .blockstate(Services.DATAGEN::feralHemp)
            .loot(Services.DATAGEN::feralHemp)
            .tag(BlockTags.SMALL_FLOWERS)
            .item()
            .tab(CreativeModeTabs.NATURAL_BLOCKS)
            .model((c, p) -> p.generated(c, p.modLoc("item/" + c.getName())))
            .build()
            .register();

    public static void register() {
        // loads this class
    }

}
