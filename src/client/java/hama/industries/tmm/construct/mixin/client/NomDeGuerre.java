package hama.industries.tmm.construct.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

@Mixin(PlayerEntityRenderer.class)
public abstract class NomDeGuerre {
    @WrapMethod(
            method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;"
    )
    public Identifier a(AbstractClientPlayerEntity abstractClientPlayerEntity, Operation<Identifier> original) {
        UUID masque = TmmConstruct.Keys.MASQUE.get(abstractClientPlayerEntity).masque();
        if (masque != null) {
            var p = MinecraftClient.getInstance().world.getPlayerByUuid(masque);
            if (p != null) {
                return ((AbstractClientPlayerEntity) p).getSkinTextures().texture();
            }
        }
        return original.call(abstractClientPlayerEntity);
    }
}

//@Mixin(PlayerEntityRenderer.class)
//public abstract class NomDeGuerre {
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
//}
