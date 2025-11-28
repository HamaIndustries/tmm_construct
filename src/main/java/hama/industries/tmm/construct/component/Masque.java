package hama.industries.tmm.construct.component;

import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import java.util.UUID;

public class Masque implements AutoSyncedComponent {
    private UUID masque = null;
    private Vec3 position = null;
    private final Player player;

    public Masque(Player p) {
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

    public void setPosition(Vec3 p) {
        this.position = p;
    }

    public void reset() {
        setMasque(null);
        if (this.position != null) {
            player.setPos(this.position);
            this.position = null;
        }
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        this.masque = compoundTag.hasUUID("tmm_masque") ? compoundTag.getUUID("tmm_masque") : null;
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        if (masque != null) {
            compoundTag.putUUID("tmm_masque", masque);
        }
    }
}
