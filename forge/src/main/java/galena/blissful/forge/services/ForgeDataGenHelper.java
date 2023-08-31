package galena.blissful.forge.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.blissful.platform.services.IDataGenHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;

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
}
