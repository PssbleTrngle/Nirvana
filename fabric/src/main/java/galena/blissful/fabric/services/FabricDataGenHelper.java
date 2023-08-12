package galena.blissful.fabric.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import galena.blissful.platform.services.IDataGenHelper;
import io.github.fabricators_of_create.porting_lib.models.generators.ConfiguredModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;

public class FabricDataGenHelper implements IDataGenHelper {

    @Override
    public void hempCrop(DataGenContext<Block, ? extends CropBlock> context, RegistrateBlockstateProvider provider) {
        var block = context.getEntry();
        provider.getVariantBuilder(block).forAllStates(state -> {
            var age = block.getAge(state);

            var base = provider.blockTexture(block);
            var name = new ResourceLocation(base.getNamespace(), base.getPath() + "_" + age);

            var model = age < 3
                    ? provider.models().cross(name.getPath(), name)
                    : provider.models().getExistingFile(name);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        });
    }

}
