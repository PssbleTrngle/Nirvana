package galena.nirvana.forge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReplaceItemLootModifier extends LootModifier {

    public static final Codec<ReplaceItemLootModifier> CODEC = RecordCodecBuilder.create(builder ->
        codecStart(builder).and(
                Codec.list(ForgeRegistries.ITEMS.getCodec()).fieldOf("items").forGetter(it -> it.items)
        ).apply(builder, ReplaceItemLootModifier::new)
    );

    private final List<Item> items;

    public ReplaceItemLootModifier(LootItemCondition[] conditions, List<Item> items) {
        super(conditions);
        this.items = items;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        var item = items.get(context.getRandom().nextInt(items.size()));
        return ObjectArrayList.of(new ItemStack(item));
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

}
