package hama.industries.tmm.construct.component;

import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class Shutter implements AutoSyncedComponent, ServerTickingComponent {
    private int ticks = 0;
    private boolean closed = false;
    private final Player player;

    public Shutter(Player p) {
        this.player = p;
    }

    public void shut(int ticks) {
        this.ticks = ticks;
    }

    private void shut(boolean closed) {
        this.closed = closed;
        TmmConstruct.Keys.SHUTTER.sync(this.player);
    }

    public boolean closed() {
        return closed;
    }

    @Override
    public void applySyncPacket(RegistryFriendlyByteBuf buf) {
        boolean old = closed;
        AutoSyncedComponent.super.applySyncPacket(buf);
        if (old != closed) {
            this.player.playSound(SoundEvents.PISTON_CONTRACT, 0.5f, 1.5f);
        }
    }

    @Override
    public boolean shouldSyncWith(ServerPlayer player) {
        return player.equals(this.player);
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        closed = compoundTag.getBoolean("tmm_shutter");
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        compoundTag.putBoolean("tmm_shutter", closed);
    }

    @Override
    public void serverTick() {
        if (ticks > 0) {
            ticks--;
            if (!closed) {
                shut(true);
            }
        } else if (closed) {
            shut(false);
        }
    }
}
