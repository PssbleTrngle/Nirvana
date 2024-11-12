package galena.nirvana.forge.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.nirvana.platform.services.IDataGenHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;

public class ForgeDataGenHelper implements IDataGenHelper {

    private void NOOP() {
        throw new IllegalStateException("DataGen should only happen in fabric");
    }

    @Override
    public void hempCrop(DataGenContext<Block, ? extends CropBlock> context, RegistrateBlockstateProvider provider) {
        NOOP();
    }

    @Override
    public void hempCrop(RegistrateBlockLootTables provider, CropBlock block) {
        NOOP();
    }

    @Override
    public void crate(DataGenContext<Block, ? extends Block> context, RegistrateBlockstateProvider provider) {
        NOOP();
    }

    @Override
    public void joint(DataGenContext<Item, ? extends Item> context, RegistrateItemModelProvider provider) {
        NOOP();
    }

    @Override
    public void blissBloom(DataGenContext<Block, ? extends DoublePlantBlock> context, RegistrateBlockstateProvider provider) {
        NOOP();
    }

    @Override
    public void blissBloom(RegistrateBlockLootTables provider, DoublePlantBlock block) {
        NOOP();
    }

    @Override
    public void wildHemp(DataGenContext<Block, ? extends Block> context, RegistrateBlockstateProvider provider) {
        NOOP();
    }

    @Override
    public void wildHemp(RegistrateBlockLootTables provider, Block block) {
        NOOP();
    }
}
