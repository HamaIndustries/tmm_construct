package hama.industries.tmm.construct.mixin.compat.hater_laser;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.doctor4t.ratatouille.index.RatatouilleItems;
import hama.industries.tmm.construct.TmmConstruct;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        RatatouilleItems.class
)
public interface UltraHaterLaser {
    @WrapOperation(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/fabricmc/loader/api/FabricLoader;isDevelopmentEnvironment()Z")
    )
    private static boolean whyWouldYouDoThisDotJpeg(FabricLoader instance, Operation<Boolean> original) {
        if (TmmConstruct.DEV) return false;
        return original.call(instance);
    }

    @Inject(
            method = "addCombatEntries",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void tc$injectHeadUnconditionalLaser(FabricItemGroupEntries fabricItemGroupEntries, CallbackInfo ci) {
        if (TmmConstruct.DEV) ci.cancel();
    }
}
