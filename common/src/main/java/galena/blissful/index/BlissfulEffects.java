package galena.blissful.index;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import galena.blissful.BlissfulConstants;
import galena.blissful.platform.Services;
import galena.blissful.world.effects.PeaceEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

public class BlissfulEffects {

    private static final AbstractRegistrate<?> REGISTRATE = Services.PLATFORM.getRegistrate();

    public static final TagKey<MobEffect> STACKING_EFFECTS = TagKey.create(Registries.MOB_EFFECT, new ResourceLocation(BlissfulConstants.MOD_ID, "stacking"));

    public static final RegistryEntry<PeaceEffect> PEACE = REGISTRATE
            .generic("peace", Registries.MOB_EFFECT, PeaceEffect::new)
            .register();

    //public static final RegistryEntry<Potion> PEACE_POTION = REGISTRATE
    //        .generic("peace", Registries.POTION, () -> new Potion(new MobEffectInstance(PEACE.get())))
    //        .register();

    public static void register() {
        // loads this class
    }

}
