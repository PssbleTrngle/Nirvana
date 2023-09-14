package galena.blissful.forge.services;

import com.tterrag.registrate.AbstractRegistrate;
import galena.blissful.forge.ForgeEntrypoint;
import galena.blissful.forge.client.JointClientExtensions;
import galena.blissful.platform.services.IPlatformHelper;
import galena.blissful.world.item.JointItem;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public AbstractRegistrate<?> getRegistrate() {
        return ForgeEntrypoint.REGISTRATE.get();
    }

    @Override
    public void registerItemRenderer(Item item, Consumer<Object> consumer) {
        if(item instanceof JointItem) {
            consumer.accept(new JointClientExtensions());
        }
    }
}
