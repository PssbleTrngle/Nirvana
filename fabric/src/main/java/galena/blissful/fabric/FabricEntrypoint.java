package galena.blissful.fabric;

import com.tterrag.registrate.Registrate;
import galena.blissful.BlissfulCommon;
import galena.blissful.BlissfulConstants;
import galena.blissful.index.BlissfulBlocks;
import galena.blissful.index.BlissfulTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public class FabricEntrypoint implements ModInitializer {

    public static final Registrate REGISTRATE = Registrate.create(BlissfulConstants.MOD_ID);

    private static final ResourceKey<PlacedFeature> FERAL_HEMP_FEATURE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BlissfulConstants.MOD_ID, "patch_feral_hemp"));

    @Override
    public void onInitialize() {
        BlissfulCommon.init();
        REGISTRATE.register();

        LootTableEvents.MODIFY.register((resources, manager, id, table, source) -> {
            if (source.isBuiltin() && BuiltInLootTables.SNIFFER_DIGGING.equals(id)) {
                table.modifyPools(it -> {
                    it.add(LootItem.lootTableItem(BlissfulBlocks.BLISS_BLOOM));
                });
            }
        });

        BiomeModifications.addFeature(BiomeSelectors.tag(BlissfulTags.GENERATES_FERAL_HEMP), GenerationStep.Decoration.VEGETAL_DECORATION, FERAL_HEMP_FEATURE);
    }

}
