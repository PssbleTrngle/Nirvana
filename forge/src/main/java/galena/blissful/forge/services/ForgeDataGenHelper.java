package galena.blissful.forge.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import galena.blissful.platform.services.IDataGenHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;

public class ForgeDataGenHelper implements IDataGenHelper {

    @Override
    public void hempCrop(DataGenContext<Block, ? extends CropBlock> context, RegistrateBlockstateProvider provider) {
        throw new IllegalStateException("DataGen should only happen in fabric");
    }

}
