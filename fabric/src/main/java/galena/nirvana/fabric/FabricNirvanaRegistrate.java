package galena.nirvana.fabric;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.providers.ProviderType;
import galena.nirvana.platform.registrate.NirvanaRegistrate;
import galena.nirvana.platform.registrate.SoundBuilder;
import io.github.fabricators_of_create.porting_lib.data.SoundDefinition;

import java.util.Optional;

public class FabricNirvanaRegistrate extends NirvanaRegistrate<FabricNirvanaRegistrate> {

    public static final ProviderType<RegistrateSoundsProvider> SOUNDS = ProviderType.register("sounds", (p, e) -> new RegistrateSoundsProvider(p, e.output(), e.helper()));

    public FabricNirvanaRegistrate(String modid) {
        super(modid);
    }

    @Override
    public SoundBuilder<NirvanaRegistrate<FabricNirvanaRegistrate>> sound(String name) {
        return entry(name, callback -> new FabricSoundBuilder<>(this, this, name, callback));
    }

    private static class FabricSoundBuilder<P> extends SoundBuilder<P> {

        public FabricSoundBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback) {
            super(owner, parent, name, callback);
            setData(SOUNDS, (context, provider) -> {
                var definition = SoundDefinition.definition();
                Optional.ofNullable(getSubtitleKey()).ifPresent(definition::subtitle);
                getSounds().forEach(id ->
                        definition.with(SoundDefinition.Sound.sound(id, SoundDefinition.SoundType.SOUND))
                );
                provider.add(context.get(), definition);
            });
        }

        @Override
        protected SoundBuilder<P> lang(String key, String translation) {
            return lang(it -> key, translation);
        }

    }

}
