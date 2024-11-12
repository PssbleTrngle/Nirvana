package galena.nirvana.fabric;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.RegistrateProvider;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import io.github.fabricators_of_create.porting_lib.data.SoundDefinition;
import io.github.fabricators_of_create.porting_lib.data.SoundDefinitionsProvider;
import net.fabricmc.api.EnvType;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;

public class RegistrateSoundsProvider extends SoundDefinitionsProvider implements RegistrateProvider {

    private final AbstractRegistrate<?> owner;

    public RegistrateSoundsProvider(AbstractRegistrate<?> owner, PackOutput output, ExistingFileHelper helper) {
        super(output, owner.getModid(), helper);
        this.owner = owner;
    }

    @Override
    public EnvType getSide() {
        return EnvType.CLIENT;
    }



    @Override
    public void add(SoundEvent soundEvent, SoundDefinition definition) {
        super.add(soundEvent, definition);
    }

    @Override
    public void registerSounds() {
        owner.genData(FabricNirvanaRegistrate.SOUNDS, this);
    }

}
