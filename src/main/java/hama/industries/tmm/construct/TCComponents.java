package hama.industries.tmm.construct;

import hama.industries.tmm.construct.component.Masque;
import hama.industries.tmm.construct.component.Masquerade;
import hama.industries.tmm.construct.component.Shutter;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class TCComponents implements EntityComponentInitializer, WorldComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(TmmConstruct.Keys.MASQUE, Masque::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(TmmConstruct.Keys.SHUTTER, Shutter::new, RespawnCopyStrategy.NEVER_COPY);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(TmmConstruct.Keys.MASQUERADE, Masquerade::new);
    }
}
