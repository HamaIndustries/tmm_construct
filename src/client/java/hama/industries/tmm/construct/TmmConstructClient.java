package hama.industries.tmm.construct;

import hama.industries.tmm.construct.component.Masque;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

public class TmmConstructClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        Masque.cb = p -> {
            if (p.equals(MinecraftClient.getInstance().player)) {
                p.getWorld().playSound(null, p.getBlockPos().up(1), TmmConstruct.MUSEUM, SoundCategory.PLAYERS, 1, 1);
            }
        };
    }
}