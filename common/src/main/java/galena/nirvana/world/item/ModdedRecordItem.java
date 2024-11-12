package galena.nirvana.world.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.RecordItem;

import java.util.function.Supplier;

public class ModdedRecordItem extends RecordItem {

    private final Supplier<SoundEvent> soundSupplier;

    /**
     * The .super constructor is passed a dummy sound event to make it work on forge,
     * the actual sound is returned at `getSound()`
     */
    public ModdedRecordItem(int comparatorLevel, Supplier<SoundEvent> sound, Properties properties, int lengthInSeconds) {
        super(comparatorLevel, SoundEvents.MUSIC_DISC_13, properties, lengthInSeconds);
        this.soundSupplier = sound;
    }

    @Override
    public SoundEvent getSound() {
        return this.soundSupplier.get();
    }
}
