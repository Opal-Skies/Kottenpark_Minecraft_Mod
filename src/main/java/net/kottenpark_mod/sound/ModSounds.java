package net.kottenpark_mod.sound;

import net.kottenpark_mod.KottenparkMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent TEST_BLOCK_BREAK = registerSoundEvent("test_block_break");
    public static final SoundEvent TEST_BLOCK_STEP = registerSoundEvent("test_block_step");
    public static final SoundEvent TEST_BLOCK_PLACE = registerSoundEvent("test_block_place");
    public static final SoundEvent TEST_BLOCK_HIT = registerSoundEvent("test_block_hit");
    public static final SoundEvent TEST_BLOCK_FALL = registerSoundEvent("test_block_fall");

    public static final BlockSoundGroup TEST_BLOCK_SOUNDS = new BlockSoundGroup(1.0f,1.0f,
            TEST_BLOCK_BREAK, TEST_BLOCK_STEP, TEST_BLOCK_PLACE, TEST_BLOCK_HIT, TEST_BLOCK_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(KottenparkMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    };

    public static void registerSounds() {
        KottenparkMod.LOGGER.info("Registering Sounds");
    }
}
