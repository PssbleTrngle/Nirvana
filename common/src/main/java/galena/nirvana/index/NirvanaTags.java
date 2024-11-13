package galena.nirvana.index;

import galena.nirvana.NirvanaConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class NirvanaTags {

    public static final TagKey<Block> SUMMER_CROPS = TagKey.create(Registries.BLOCK, new ResourceLocation("sereneseasons", "summer_crops"));

    public static final TagKey<Item> NAUSEATING = TagKey.create(Registries.ITEM, new ResourceLocation(NirvanaConstants.MOD_ID, "nauseating"));

    public static final TagKey<Item> ATTACHED_TO_HEAD = TagKey.create(Registries.ITEM, new ResourceLocation(NirvanaConstants.MOD_ID, "attached_to_head"));

    public static final TagKey<Biome> GENERATES_WILD_HEMP = TagKey.create(Registries.BIOME, new ResourceLocation(NirvanaConstants.MOD_ID, "has_feature/wild_hemp"));

    public static final TagKey<Item> SHEARS = TagKey.create(Registries.ITEM, new ResourceLocation(NirvanaConstants.MOD_ID, "shears"));

    public static final TagKey<Item> SMOKING_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(NirvanaConstants.MOD_ID, "smoking_item"));

}
