package galena.blissful.fabric.datagen;

import galena.blissful.BlissfulCommon;
import galena.blissful.index.BlissfulEffects;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import static galena.blissful.fabric.FabricEntrypoint.REGISTRATE;

public class FabricDataGenerators implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        BlissfulCommon.init();
        addDefaultTranslations();

        var fileHelper = ExistingFileHelper.withResourcesFromArg();
        REGISTRATE.setupDatagen(generator.createPack(), fileHelper);
    }

    private void addDefaultTranslations() {
        var peaceId = BlissfulEffects.PEACE.getId();
        REGISTRATE.addLang("effect", peaceId, "Peace");
        REGISTRATE.addLang("effect", peaceId, "description", "A blissful aura befogs your brain");
    }

}
