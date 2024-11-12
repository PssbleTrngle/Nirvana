package galena.nirvana.platform.registrate;

import com.tterrag.registrate.AbstractRegistrate;

public abstract class NirvanaRegistrate<T extends NirvanaRegistrate<T>> extends AbstractRegistrate<T> {

    protected NirvanaRegistrate(String modid) {
        super(modid);
    }

    public abstract SoundBuilder<NirvanaRegistrate<T>> sound(String name);

    public final SoundBuilder<NirvanaRegistrate<T>> sound() {
        return  sound(currentName());
    }

}
