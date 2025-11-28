package hama.industries.tmm.construct.mixin.client;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GameRenderer.class)
public class Clair {
//    @WrapMethod(
//            method = "render"
//    )
//    public void obscur(DeltaTracker deltaTracker, boolean bl, Operation<Void> original) {
//        Player p = Minecraft.getInstance().player;
//        if (p == null || !TmmConstruct.Keys.SHUTTER.get(p).closed()) {
//            original.call(deltaTracker, bl);
//        }
//    }
}
