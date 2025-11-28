package hama.industries.tmm.construct;

import hama.industries.tmm.construct.component.Masque;
import hama.industries.tmm.construct.component.Masquerade;
import hama.industries.tmm.construct.component.Shutter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TmmConstruct implements ModInitializer {
    public static final String MOD_ID = "tmm-construct";

    public static Identifier id(String id) {
        return Identifier.of(MOD_ID, id);
    }

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Item THETISCOPE = Registry.register(Registries.ITEM, id("thetiscope"), new Item(new Item.Settings().maxCount(1)));

    public static final SoundEvent MUSEUM = Registry.register(Registries.SOUND_EVENT, id("et8"), SoundEvent.of(id("et8")));

    public static final boolean DEV = true;

    @Override
    public void onInitialize() {

    }


    public static final class Keys {
        public static final ComponentKey<Shutter> SHUTTER =
                ComponentRegistry.getOrCreate(id("shutter"), Shutter.class);
        public static final ComponentKey<Masquerade> MASQUERADE =
                ComponentRegistry.getOrCreate(id("masquerade"), Masquerade.class);
        public static final ComponentKey<Masque> MASQUE =
                ComponentRegistry.getOrCreate(id("masque"), Masque.class);
    }
}