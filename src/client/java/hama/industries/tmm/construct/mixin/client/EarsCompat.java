package hama.industries.tmm.construct.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.unascribed.ears.EarsFeatureRenderer;
import hama.industries.tmm.construct.TmmConstructClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(value = EarsFeatureRenderer.class, remap = false)
public class EarsCompat {
    @WrapMethod(
            method = "Lcom/unascribed/ears/EarsFeatureRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/network/AbstractClientPlayerEntity;FFFFFF)V"
    )
    public void tc$ehehe(MatrixStack m, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, Operation<Void> original) {
        original.call(m, vertexConsumers, light, (AbstractClientPlayerEntity) TmmConstructClient.tc$masque(entity), limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
    }
}
