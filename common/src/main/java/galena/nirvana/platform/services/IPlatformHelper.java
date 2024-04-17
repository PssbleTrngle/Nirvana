package galena.nirvana.platform.services;

import com.tterrag.registrate.AbstractRegistrate;
import galena.nirvana.world.item.JointItem;
import net.minecraft.world.item.Item;

public interface IPlatformHelper {

    AbstractRegistrate<?> getRegistrate();

    default JointItem createJointItem(Item.Properties properties) {
        return new JointItem(properties);
    }
}
