package hama.industries.tmm.construct.mixin.compat;

import dev.doctor4t.trainmurdermystery.api.TMMRoles;
import dev.doctor4t.trainmurdermystery.cca.GameWorldComponent;
import dev.doctor4t.trainmurdermystery.cca.PlayerShopComponent;
import dev.doctor4t.trainmurdermystery.game.MurderGameMode;
import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(MurderGameMode.class)
public class MurderGameModeMixin {
    @Inject(
            method = "assignRolesAndGetKillerCount", at = @At("RETURN")
    )
    private static void tc$kill(@NotNull ServerWorld world, @NotNull List<ServerPlayerEntity> players, GameWorldComponent gameComponent, CallbackInfoReturnable<Integer> cir) {
        if (TmmConstruct.DEV) {
            gameComponent.addRole(players.get(0).getUuid(), TMMRoles.KILLER);
        }
    }

    @Inject(
            method = "tickServerGameLoop",
            at = @At("HEAD"),
            cancellable = true)
    private void tc$moneyGlitch(ServerWorld serverWorld, GameWorldComponent gameWorldComponent, CallbackInfo ci) {
        for (ServerPlayerEntity player : serverWorld.getPlayers()) {
            PlayerShopComponent.KEY.get(player).setBalance(500);
        }
        if (TmmConstruct.DEV) {
            ci.cancel();
        }
    }
}
