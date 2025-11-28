package hama.industries.tmm.construct.component;

import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import java.util.UUID;
import java.util.function.Consumer;

public class Masque implements AutoSyncedComponent {
    private UUID masque = null;
    private Vec3d position = null;
    private final PlayerEntity player;

    public Masque(PlayerEntity p) {
        this.player = p;
    }

    @Nullable
    public UUID masque() {
        return masque;
    }

    public void setMasque(UUID id) {
        masque = id;
        TmmConstruct.Keys.MASQUE.sync(player);
    }

    public void setPosition(Vec3d p) {
        this.position = p;
    }

    public void reset() {
        setMasque(null);
        if (this.position != null) {
            player.setPosition(this.position);
            this.position = null;
        }
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.masque = nbtCompound.containsUuid("tmm_masque") ? nbtCompound.getUuid("tmm_masque") : null;

    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (masque != null) {
            nbtCompound.putUuid("tmm_masque", masque);
        }
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        var prev = masque;
        AutoSyncedComponent.super.applySyncPacket(buf);
//        if (masque != null && masque != prev) {
//            cb.accept(this.player);
//        }
    }

    // sorry guys
    public static Consumer<PlayerEntity> cb = p -> {
    };
}
