package galena.nirvana.forge;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import galena.nirvana.NirvanaCommon;
import galena.nirvana.NirvanaConstants;
import galena.nirvana.forge.client.ForgeClientEntrypoint;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(NirvanaConstants.MOD_ID)
public class ForgeEntrypoint {

    public static final NonNullSupplier<ForgeNirvanaRegistrate> REGISTRATE = NonNullSupplier.lazy(() -> ForgeNirvanaRegistrate.create(NirvanaConstants.MOD_ID));

    public ForgeEntrypoint() {
        NirvanaCommon.init();

        //noinspection Convert2MethodRef
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ForgeClientEntrypoint.init());

        REGISTRATE.get()
                .object("replace_item")
                .generic(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, () -> ReplaceItemLootModifier.CODEC)
                .register();
    }

}
