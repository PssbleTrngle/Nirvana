package galena.nirvana.fabric;

import com.tterrag.registrate.Registrate;
import galena.nirvana.NirvanaCommon;
import galena.nirvana.NirvanaConstants;
import galena.nirvana.index.NirvanaBlocks;
import galena.nirvana.index.NirvanaTags;
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

    public static final Registrate REGISTRATE = Registrate.create(NirvanaConstants.MOD_ID);

    private static final ResourceKey<PlacedFeature> FERAL_HEMP_FEATURE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(NirvanaConstants.MOD_ID, "patch_feral_hemp"));

    @Override
    public void onInitialize() {
        NirvanaCommon.init();
        REGISTRATE.register();

        LootTableEvents.MODIFY.register((resources, manager, id, table, source) -> {
            if (source.isBuiltin() && BuiltInLootTables.SNIFFER_DIGGING.equals(id)) {
                table.modifyPools(it -> {
                    it.add(LootItem.lootTableItem(NirvanaBlocks.BLISS_BLOOM));
                });
            }
        });

        BiomeModifications.addFeature(BiomeSelectors.tag(NirvanaTags.GENERATES_FERAL_HEMP), GenerationStep.Decoration.VEGETAL_DECORATION, FERAL_HEMP_FEATURE);
    }

}
