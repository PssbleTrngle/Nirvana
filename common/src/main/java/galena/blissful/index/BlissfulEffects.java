package galena.blissful.index;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import galena.blissful.platform.Services;
import galena.blissful.world.effects.PeaceMobEffect;
import net.minecraft.core.registries.Registries;

public class BlissfulEffects {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final RegistryEntry<PeaceMobEffect> PEACE = REGISTRATE
            .generic("peace", Registries.MOB_EFFECT, PeaceMobEffect::new)
            .register();

    //public static final RegistryEntry<Potion> PEACE_POTION = REGISTRATE
    //        .generic("peace", Registries.POTION, () -> new Potion(new MobEffectInstance(PEACE.get())))
    //        .register();

    public static void register() {
        // loads this class
    }

}
