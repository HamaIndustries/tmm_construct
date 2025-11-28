package hama.industries.tmm.construct.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftClient.class)
public abstract class Clair {

    @WrapOperation(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gl/Framebuffer;draw(II)V")
    )
    public void tc$shut(Framebuffer instance, int width, int height, Operation<Void> original) {
        PlayerEntity p = MinecraftClient.getInstance().player;
        if (p == null || !TmmConstruct.Keys.SHUTTER.get(p).closed()) {
            original.call(instance, width, height);
        }
    }


//    @Shadow
//    @Final
//    private BufferBuilderStorage buffers;
//
//    @WrapMethod(
//            method = "render"
//    )
//    public void obscur(RenderTickCounter tickCounter, boolean tick, Operation<Void> original) {
//        PlayerEntity p = MinecraftClient.getInstance().player;
//        if (p == null || !TmmConstruct.Keys.SHUTTER.get(p).closed()) {
//            original.call(tickCounter, tick);
//        }
//    }
}
