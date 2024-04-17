package galena.nirvana.fabric.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.nirvana.NirvanaConstants;
import galena.nirvana.index.NirvanaItems;
import galena.nirvana.index.NirvanaTags;
import galena.nirvana.platform.services.IDataGenHelper;
import galena.nirvana.world.block.HempCropBlock;
import io.github.fabricators_of_create.porting_lib.models.generators.ConfiguredModel;
import io.github.fabricators_of_create.porting_lib.models.generators.ModelFile;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class FabricDataGenHelper implements IDataGenHelper {

    private ResourceLocation withSuffix(ResourceLocation base, String suffix) {
        return new ResourceLocation(base.getNamespace(), base.getPath() + suffix);
    }

    private LootItemCondition.Builder hasAge(Block block, int age) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(HempCropBlock.AGE, age)
                );
    }

    private LootItemCondition.Builder growthMissing(CropBlock block, int away) {
        return hasAge(block, block.getMaxAge() - away);
    }

    private LootItemCondition.Builder maxAge(CropBlock block) {
        return growthMissing(block, 0);
    }

    @Override
    public void hempCrop(DataGenContext<Block, ? extends CropBlock> context, RegistrateBlockstateProvider provider) {
        var block = context.getEntry();
        provider.getVariantBuilder(block).forAllStates(state -> {
            var age = block.getAge(state);

            var base = provider.blockTexture(block);
            var name = withSuffix(base, "_" + age);

            var model = age < 3
                    ? provider.models().singleTexture(name.getPath(), new ResourceLocation(NirvanaConstants.MOD_ID, "block/crop_cross"), "cross", name)
                    : provider.models().getExistingFile(name);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        });
    }

    @Override
    public void hempCrop(RegistrateBlockLootTables provider, CropBlock block) {
        provider.add(block, provider.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                                .add(AlternativesEntry.alternatives(
                                                LootItem.lootTableItem(NirvanaItems.HEMP)
                                                        .when(maxAge(block))
                                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.57F, 2)),
                                                LootItem.lootTableItem(NirvanaItems.HEMP)
                                                        .when(growthMissing(block, -1))
                                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.62F, 1)),
                                                LootItem.lootTableItem(NirvanaItems.HEMP_SEEDS)
                                        )
                                ))
                        .withPool(LootPool.lootPool()
                                .add(AlternativesEntry.alternatives(
                                                LootItem.lootTableItem(NirvanaItems.HEMP_SEEDS)
                                                        .when(maxAge(block))
                                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.57F, 2)),
                                                LootItem.lootTableItem(NirvanaItems.HEMP_SEEDS)
                                                        .when(growthMissing(block, -1))
                                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.57F, 1))
                                        )
                                )
                        )
        ));
    }

    @Override
    public void crate(DataGenContext<Block, ? extends Block> context, RegistrateBlockstateProvider provider) {
        var block = context.getEntry();
        var name = provider.blockTexture(block);
        var model = provider.models().cube(
                name.getPath(),
                withSuffix(name, "_bottom"),
                withSuffix(name, "_top"),
                withSuffix(name, "_front"),
                withSuffix(name, "_back"),
                withSuffix(name, "_side"),
                withSuffix(name, "_side")
        ).texture("particle", withSuffix(name, "_top"));

        provider.getVariantBuilder(block).forAllStates(state -> {
            var facing = state.getValue(HorizontalDirectionalBlock.FACING);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY((int) facing.toYRot())
                    .build();
        });
    }

    @Override
    public void joint(DataGenContext<Item, ? extends Item> context, RegistrateItemModelProvider provider) {
        provider.withExistingParent(context.getName() + "_flat", "item/generated").texture("layer0", provider.itemTexture(context));
        provider.getBuilder(context.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity"));
    }

    @Override
    public void blissBloom(DataGenContext<Block, ? extends DoublePlantBlock> context, RegistrateBlockstateProvider provider) {
        provider.getVariantBuilder(context.get()).forAllStates(state -> {
            var half = state.getValue(DoublePlantBlock.HALF);
            var name = "block/" + context.getName() + "_" + half.getSerializedName();
            var model = provider.models().cross(name, provider.modLoc(name));
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        });
    }

    @Override
    public void blissBloom(RegistrateBlockLootTables provider, DoublePlantBlock block) {
        provider.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                .when(ExplosionCondition.survivesExplosion())
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                        StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                ))
                .add(LootItem.lootTableItem(block))
        ));
    }

    @Override
    public void feralHemp(DataGenContext<Block, ? extends Block> context, RegistrateBlockstateProvider provider) {
        provider.getVariantBuilder(context.get()).forAllStates($ -> {
            var name = context.getName();
            var suffixes = Stream.of("", "_2", "_3");

            return suffixes.map(suffix -> {
                var builder = ConfiguredModel.builder();
                var model = provider.models().getExistingFile(provider.modLoc("block/" + name + suffix));
                return builder.modelFile(model).build()[0];
            }).toArray(ConfiguredModel[]::new);
        });
    }

    @Override
    public void feralHemp(RegistrateBlockLootTables provider, Block block) {
        provider.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                .when(ExplosionCondition.survivesExplosion())
                .add(AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block)
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(NirvanaTags.SHEARS))),
                        LootItem.lootTableItem(NirvanaItems.HEMP)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0F, 2F)))
                ))
        ));
    }
}
