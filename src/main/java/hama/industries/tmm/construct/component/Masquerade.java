package hama.industries.tmm.construct.component;

import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Masquerade implements ServerTickingComponent {
    private static final int TOTAL_TICKS = 20 * 20;
    private int ticks = 0;
    private boolean begin = false;
    private boolean end = false;
    private World level;

    public Masquerade(World level) {
        this.level = level;
    }

    public boolean waltz() {
        if (ticks != 0) return false;
        begin = true;
        ticks = TOTAL_TICKS;
        for (var p : level.getPlayers()) {
            TmmConstruct.Keys.SHUTTER.get(p).shut(40);
        }
        return true;
    }

    private boolean prosopagnosticate() {
        if (ticks >= TOTAL_TICKS - 25) return false;
        List<? extends PlayerEntity> dancers = level.getPlayers();
        List<PlayerEntity> others = new ArrayList<>(dancers);
        Collections.shuffle(others);
        List<Vec3d> newPos = others.stream().map(Entity::getPos).toList();

        for (int i = 0; i < dancers.size(); i++) {
            Masque masque = TmmConstruct.Keys.MASQUE.get(dancers.get(i));
            masque.setMasque(others.get(i).getUuid());
            masque.setPosition(dancers.get(i).getPos());
            dancers.get(i).setPosition(newPos.get(i));
        }

//        List<UUID> masques = new ArrayList<>(dancers.stream().map(Entity::getUuid).toList());
//        Collections.shuffle(masques);
//        List<Vec3d> pos = new ArrayList<>(dancers.stream().map(d -> d.getPos()).toList());
//        Collections.shuffle(pos);
//        for (int i = 0; i < dancers.size(); i++) {
//            Masque masque = TmmConstruct.Keys.MASQUE.get(dancers.get(i));
//            masque.setMasque(masques.get(i));
//            masque.setPosition(dancers.get(i).getPos());
//        }
        dancers.get(0).getWorld().playSound(null, dancers.get(0).getBlockPos().up(10), TmmConstruct.MUSEUM, SoundCategory.AMBIENT, 1000, 1);
        return true;
    }

    private void conclude() {
        for (var dancer : level.getPlayers()) {
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
                for (var p : level.getPlayers()) {
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
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {

    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {

    }
}
