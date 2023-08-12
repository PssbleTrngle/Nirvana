package galena.blissful.index;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import galena.blissful.platform.Services;
import galena.blissful.world.block.HempCropBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;

import static galena.blissful.platform.Services.DATAGEN;

public class BlissfulBlocks {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final BlockEntry<HempCropBlock> HEMP = REGISTRATE.block("hemp", HempCropBlock::new)
            .initialProperties(() -> Blocks.WHEAT)
            .addLayer(() -> RenderType::cutout)
            .blockstate(DATAGEN::hempCrop)
            .loot((tables, block) -> {
                var condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(HempCropBlock.AGE, block.getMaxAge())
                        );
                tables.add(block, tables.createCropDrops(block, BlissfuItems.HEMP_SEEDS.get(), BlissfuItems.HEMP.get(), condition));
            })
            .register();

    public static void register() {
        // loads this class
    }

}
