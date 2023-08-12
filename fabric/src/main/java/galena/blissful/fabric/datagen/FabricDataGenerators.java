package galena.blissful.fabric.datagen;

import galena.blissful.fabric.FabricEntrypoint;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FabricDataGenerators implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricEntrypoint.init();

        var fileHelper = ExistingFileHelper.withResourcesFromArg();
        FabricEntrypoint.REGISTRATE.setupDatagen(generator.createPack(), fileHelper);
    }

}
