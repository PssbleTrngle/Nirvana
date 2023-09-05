package galena.blissful.forge;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import galena.blissful.BlissfulCommon;
import galena.blissful.BlissfulConstants;
import net.minecraftforge.fml.common.Mod;

@Mod(BlissfulConstants.MOD_ID)
public class ForgeEntrypoint {

    public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(BlissfulConstants.MOD_ID));

    public ForgeEntrypoint() {
        BlissfulCommon.init();

        //noinspection Convert2MethodRef
        //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ForgeClientEntrypoint.init());
    }

}
