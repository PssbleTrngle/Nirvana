package galena.blissful.fabric.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.blissful.BlissfulCommon;
import galena.blissful.index.BlissfuItems;
import galena.blissful.index.BlissfulEffects;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.PotionUtils;

import static galena.blissful.fabric.FabricEntrypoint.REGISTRATE;

public class FabricDataGenerators implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        BlissfulCommon.init();
        addDefaultTranslations();

        var fileHelper = ExistingFileHelper.withResourcesFromArg();
        var pack = generator.createPack();
        REGISTRATE.setupDatagen(pack, fileHelper);
        pack.addProvider(AdditionalBlissfulRecipes::new);
    }

    private void addDefaultTranslations() {
        var peaceId = BlissfulEffects.PEACE.getId();
        REGISTRATE.addLang("effect", peaceId, "Peace");
        REGISTRATE.addLang("effect", peaceId, "description", "A blissful aura befogs your brain");

        REGISTRATE.addLang("item", BlissfuItems.POTION_BONG.getId(), "effect.empty", "Effect Bong");

        BuiltInRegistries.POTION.forEach(potion -> {
            var id = potion.getName("");
            var stack = PotionUtils.setPotion(BlissfuItems.POTION_BONG.asStack(), potion);
            if (potion.getEffects().isEmpty()) {
                REGISTRATE.addRawLang(stack.getDescriptionId(), RegistrateLangProvider.toEnglishName(id) + " Bong");
            } else {
                REGISTRATE.addRawLang(stack.getDescriptionId(), "Bong of " + RegistrateLangProvider.toEnglishName(id));
            }
        });
    }

}
