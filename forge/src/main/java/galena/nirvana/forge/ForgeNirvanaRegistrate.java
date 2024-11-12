package galena.nirvana.forge;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BuilderCallback;
import galena.nirvana.platform.registrate.NirvanaRegistrate;
import galena.nirvana.platform.registrate.SoundBuilder;

public class ForgeNirvanaRegistrate extends NirvanaRegistrate<ForgeNirvanaRegistrate> {

    public static ForgeNirvanaRegistrate create(String modid) {
        var registrate =  new ForgeNirvanaRegistrate(modid);
        registrate.registerEventListeners(registrate.getModEventBus());
        return registrate;
    }

    private ForgeNirvanaRegistrate(String modid) {
        super(modid);
    }

    @Override
    public SoundBuilder<NirvanaRegistrate<ForgeNirvanaRegistrate>> sound(String name) {
        return entry(name, callback -> new ForgeSoundBuilder<>(this, this, name, callback));
    }

    private static class ForgeSoundBuilder<P> extends SoundBuilder<P> {

        public ForgeSoundBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback) {
            super(owner, parent, name, callback);
        }

        @Override
        protected SoundBuilder<P> lang(String key, String translation) {
            return lang(it -> key, translation);
        }

    }

}
