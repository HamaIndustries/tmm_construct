package hama.industries.tmm.construct.component;

import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class Shutter implements AutoSyncedComponent, ServerTickingComponent {
    private int ticks = 0;
    private boolean closed = false;
    private final PlayerEntity player;

    public Shutter(PlayerEntity p) {
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
    public void applySyncPacket(RegistryByteBuf buf) {
        boolean old = closed;
        AutoSyncedComponent.super.applySyncPacket(buf);
        if (old != closed) {
            this.player.playSound(SoundEvents.BLOCK_PISTON_CONTRACT, 0.5f, 1.5f);
        }
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player.equals(this.player);
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        closed = nbtCompound.getBoolean("tmm_shutter");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("tmm_shutter", closed);
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
