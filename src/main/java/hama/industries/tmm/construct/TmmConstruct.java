package hama.industries.tmm.construct;

import hama.industries.tmm.construct.component.Masque;
import hama.industries.tmm.construct.component.Masquerade;
import hama.industries.tmm.construct.component.Shutter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TmmConstruct implements ModInitializer, EntityComponentInitializer, WorldComponentInitializer {
    public static final String MOD_ID = "tmm-construct";

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(Keys.MASQUE, Masque::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(Keys.SHUTTER, Shutter::new, RespawnCopyStrategy.NEVER_COPY);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(Keys.MASQUERADE, Masquerade::new);
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