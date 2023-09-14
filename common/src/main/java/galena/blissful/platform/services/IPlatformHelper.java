package galena.blissful.platform.services;

import com.tterrag.registrate.AbstractRegistrate;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public interface IPlatformHelper {

    AbstractRegistrate<?> getRegistrate();

    void registerItemRenderer(Item item, Consumer<Object> consumer);
}
