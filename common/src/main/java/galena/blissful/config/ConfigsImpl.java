package galena.blissful.config;

import galena.blissful.platform.services.IConfigs;

public class ConfigsImpl implements IConfigs {

    private final BlissfulCommonConfig common = new BlissfulCommonConfig();

    @Override
    public BlissfulCommonConfig common() {
        return common;
    }
}
