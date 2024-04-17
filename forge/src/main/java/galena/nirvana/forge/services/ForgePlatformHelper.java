package galena.nirvana.forge.services;

import com.tterrag.registrate.AbstractRegistrate;
import galena.nirvana.forge.ForgeEntrypoint;
import galena.nirvana.forge.world.ForgeJointItem;
import galena.nirvana.platform.services.IPlatformHelper;
import galena.nirvana.world.item.JointItem;
import net.minecraft.world.item.Item;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public AbstractRegistrate<?> getRegistrate() {
        return ForgeEntrypoint.REGISTRATE.get();
    }

    @Override
    public JointItem createJointItem(Item.Properties properties) {
        return new ForgeJointItem(properties);
    }
}
