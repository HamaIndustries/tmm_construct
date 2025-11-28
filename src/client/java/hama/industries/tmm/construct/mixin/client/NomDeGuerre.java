package hama.industries.tmm.construct.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.UUID;

@Mixin(LivingEntityRenderer.class)
public abstract class NomDeGuerre {
    @Unique
    private LivingEntity tc$masque(LivingEntity image) {
        if (image instanceof AbstractClientPlayerEntity player) {
            UUID masque = TmmConstruct.Keys.MASQUE.get(player).masque();
            if (masque != null) {
                var p = MinecraftClient.getInstance().world.getPlayerByUuid(masque);
                image = p == null ? image : p;
            }
        }
        return image;
    }

//    @WrapOperation(
//            method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/entity/LivingEntity;ZZZ)Lnet/minecraft/client/render/RenderLayer;")
//    ) public @Nullable <T extends LivingEntity, M extends EntityModel<T>> RenderLayer tc$impersonate(LivingEntityRenderer<T, M> instance, T entity, boolean showBody, boolean translucent, boolean showOutline, Operation<RenderLayer> original) {
//        return original.call(instance, tc$masque(entity), showBody, translucent, showOutline);
//    }

    @ModifyVariable(
            method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/entity/LivingEntity;ZZZ)Lnet/minecraft/client/render/RenderLayer;")
    ) public LivingEntity tc$impersonate_(LivingEntity preimage){
        return tc$masque(preimage);
    }

//    @WrapMethod(
//            method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;"
//    )
//    public Identifier a(AbstractClientPlayerEntity abstractClientPlayerEntity, Operation<Identifier> original) {
//        UUID masque = TmmConstruct.Keys.MASQUE.get(abstractClientPlayerEntity).masque();
//        if (masque != null) {
//            var p = MinecraftClient.getInstance().world.getPlayerByUuid(masque);
//            if (p != null) {
//                return ((AbstractClientPlayerEntity) p).getSkinTextures().texture();
//            }
//        }
//        return original.call(abstractClientPlayerEntity);
//    }
}