package hama.industries.tmm.construct.mixin.client;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerRenderer.class)
public abstract class NomDeGuerre {
//    @WrapMethod(
//            method = "getTextureLocation(Lnet/minecraft/client/player/AbstractClientPlayer;)Lnet/minecraft/resources/ResourceLocation;"
//    )
//    public ResourceLocation a(AbstractClientPlayer abstractClientPlayer, Operation<ResourceLocation> original) {
//        UUID masque = TmmConstruct.Keys.MASQUE.get(abstractClientPlayer).masque();
//        if (masque != null) {
//            var p = Minecraft.getInstance().level.getPlayerByUUID(masque);
//            if (p != null) {
//                return ((AbstractClientPlayer) p).getSkin().texture();
//            }
//        }
//        return original.call(abstractClientPlayer);
//    }
}
