package hama.industries.tmm.construct.mixin.client.client_hater_laser;

import dev.doctor4t.ratatouille.client.RatatouilleClient;
import hama.industries.tmm.construct.TmmConstruct;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RatatouilleClient.class)
public class ClientLaser {
    @Inject(
            method = "registerTestFeatures",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void tc$no(CallbackInfo ci) {
        if (TmmConstruct.DEV) ci.cancel();
    }
}
