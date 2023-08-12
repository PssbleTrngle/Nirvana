package galena.blissful.fabric.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.blissful.platform.Services;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

public class LangGen extends RegistrateLangProvider {

    public LangGen(FabricDataOutput pack) {
        super(Services.PLATFORM.getRegistrate(), pack);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        super.generateTranslations(builder);
    }
}
