package galena.blissful.world.item;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class LazyFoodItem extends Item {

    private final NonNullSupplier<FoodProperties> foodProperties;

    public LazyFoodItem(Properties properties, NonNullSupplier<FoodProperties> foodProperties) {
        super(properties);
        this.foodProperties = foodProperties;
    }

    @Nullable
    @Override
    public FoodProperties getFoodProperties() {
        return foodProperties.get();
    }

    @Override
    public boolean isEdible() {
        return true;
    }
}
