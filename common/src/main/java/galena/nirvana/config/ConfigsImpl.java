package galena.nirvana.config;

import galena.nirvana.platform.services.IConfigs;

public class ConfigsImpl implements IConfigs {

    private final NirvanaCommonConfig common = new NirvanaCommonConfig();

    @Override
    public NirvanaCommonConfig common() {
        return common;
    }
}
