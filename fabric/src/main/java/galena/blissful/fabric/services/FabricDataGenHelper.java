package galena.blissful.fabric.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import galena.blissful.BlissfulConstants;
import galena.blissful.platform.services.IDataGenHelper;
import io.github.fabricators_of_create.porting_lib.models.generators.ConfiguredModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

public class FabricDataGenHelper implements IDataGenHelper {

    private ResourceLocation withSuffix(ResourceLocation base, String suffix) {
        return new ResourceLocation(base.getNamespace(), base.getPath() + suffix);
    }

    @Override
    public void hempCrop(DataGenContext<Block, ? extends CropBlock> context, RegistrateBlockstateProvider provider) {
        var block = context.getEntry();
        provider.getVariantBuilder(block).forAllStates(state -> {
            var age = block.getAge(state);

            var base = provider.blockTexture(block);
            var name = withSuffix(base, "_" + age);

            var model = age < 3
                    ? provider.models().singleTexture(name.getPath(), new ResourceLocation(BlissfulConstants.MOD_ID, "block/crop_cross"), "cross", name)
                    : provider.models().getExistingFile(name);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        });
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
}
