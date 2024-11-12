package galena.nirvana.platform.services;

import galena.nirvana.platform.registrate.NirvanaRegistrate;
import galena.nirvana.world.item.JointItem;
import net.minecraft.world.item.Item;

public interface IPlatformHelper {

    NirvanaRegistrate<?> getRegistrate();

    default JointItem createJointItem(Item.Properties properties) {
        return new JointItem(properties);
    }
}
