package galena.blissful.platform.services;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;

public interface IDataGenHelper {

    void hempCrop(DataGenContext<Block, ? extends CropBlock> context, RegistrateBlockstateProvider provider);

    void crate(DataGenContext<Block, ? extends Block> context, RegistrateBlockstateProvider provider);

}
