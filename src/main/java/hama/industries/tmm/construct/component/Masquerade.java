package hama.industries.tmm.construct.component;

import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Masquerade implements ServerTickingComponent {
    private static final int TOTAL_TICKS = 20 * 10;
    private int ticks = 0;
    private boolean begin = false;
    private boolean end = false;
    private Level level;
    private Map<UUID, Vec3> positions = new HashMap<>();

    public Masquerade(Level level) {
        this.level = level;
    }

    public boolean waltz() {
        begin = true;
        ticks = TOTAL_TICKS;
        for (var p : level.players()) {
            TmmConstruct.Keys.SHUTTER.get(p).shut(40);
        }
        return true;
    }

    private boolean prosopagnosticate() {
        if (ticks >= TOTAL_TICKS - 25) return false;
        List<? extends Player> dancers = level.players();
        List<UUID> masques = new ArrayList<>(dancers.stream().map(Entity::getUUID).toList());
        Collections.shuffle(masques);
        List<Vec3> pos = new ArrayList<>(dancers.stream().map(Entity::position).toList());
        Collections.shuffle(pos);
        for (int i = 0; i < dancers.size(); i++) {
            Masque masque = TmmConstruct.Keys.MASQUE.get(dancers.get(i));
            masque.setMasque(masques.get(i));
            masque.setPosition(dancers.get(i).position());
        }
        return true;
    }

    private void conclude() {
        for (var dancer : level.players()) {
            TmmConstruct.Keys.MASQUE.get(dancer).reset();
        }
    }

    @Override
    public void serverTick() {
        if (ticks > 0) {
            ticks--;
            if (begin) {
                begin = !prosopagnosticate();
            } else if (ticks < 20 && !end) {
                end = true;
                for (var p : level.players()) {
                    TmmConstruct.Keys.SHUTTER.get(p).shut(40);
                }
            } else if (ticks == 0) {
                conclude();
            }
        } else {
            begin = false;
            end = false;
        }
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {

    }

    @Override
    public void writeToNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {

    }
}
