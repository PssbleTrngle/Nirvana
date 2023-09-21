package galena.blissful.forge.world;

import galena.blissful.forge.client.JointClientExtensions;
import galena.blissful.world.item.JointItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ForgeJointItem  extends JointItem {

    public ForgeJointItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new JointClientExtensions());
    }
}
