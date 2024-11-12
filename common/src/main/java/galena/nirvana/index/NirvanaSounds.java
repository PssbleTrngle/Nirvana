package galena.nirvana.index;

import com.tterrag.registrate.util.entry.RegistryEntry;
import galena.nirvana.platform.Services;
import galena.nirvana.platform.registrate.NirvanaRegistrate;
import net.minecraft.sounds.SoundEvent;

public class NirvanaSounds {

    private static final NirvanaRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final RegistryEntry<SoundEvent> JAM = REGISTRATE
            .sound("music.disc.jam")
            .with("discs/jam")
            .register();

    public static void register() {
        // loads this class
    }

}
