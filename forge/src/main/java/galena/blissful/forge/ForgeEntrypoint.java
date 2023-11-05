package galena.blissful.forge;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import galena.blissful.BlissfulCommon;
import galena.blissful.BlissfulConstants;
import galena.blissful.forge.client.ForgeClientEntrypoint;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(BlissfulConstants.MOD_ID)
public class ForgeEntrypoint {

    public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(BlissfulConstants.MOD_ID));

    public ForgeEntrypoint() {
        BlissfulCommon.init();

        //noinspection Convert2MethodRef
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ForgeClientEntrypoint.init());

        REGISTRATE.get()
                .object("replace_item")
                .generic(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, () -> ReplaceItemLootModifier.CODEC)
                .register();
    }

}
