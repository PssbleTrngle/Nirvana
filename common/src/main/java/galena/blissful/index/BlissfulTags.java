package galena.blissful.index;

import galena.blissful.BlissfulConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlissfulTags {

    public static final TagKey<Block> SUMMER_CROPS = TagKey.create(Registries.BLOCK, new ResourceLocation("sereneseasons", "summer_crops"));

    public static final TagKey<Item> NAUSEATING = TagKey.create(Registries.ITEM, new ResourceLocation(BlissfulConstants.MOD_ID, "nauseating"));

    public static final TagKey<Item> ATTACHED_TO_HEAD = TagKey.create(Registries.ITEM, new ResourceLocation(BlissfulConstants.MOD_ID, "attached_to_head"));

}
