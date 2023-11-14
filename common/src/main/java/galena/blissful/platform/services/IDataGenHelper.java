package galena.blissful.platform.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;

public interface IDataGenHelper {

    void hempCrop(DataGenContext<Block, ? extends CropBlock> context, RegistrateBlockstateProvider provider);

    void hempCrop(RegistrateBlockLootTables provider, CropBlock block);

    void crate(DataGenContext<Block, ? extends Block> context, RegistrateBlockstateProvider provider);

    void joint(DataGenContext<Item, ? extends Item> context, RegistrateItemModelProvider provider);

    void blissBloom(DataGenContext<Block, ? extends DoublePlantBlock> context, RegistrateBlockstateProvider provider);

    void blissBloom(RegistrateBlockLootTables provider, DoublePlantBlock block);

    void feralHemp(DataGenContext<Block, ? extends Block> context, RegistrateBlockstateProvider provider);

    void feralHemp(RegistrateBlockLootTables provider, Block block);
}
