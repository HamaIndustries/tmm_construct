package hama.industries.tmm.construct.mixin.compat.hater_laser;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CommandManager.class)
public class CommandLaser {

    @ModifyExpressionValue(
            method = "<init>"
            , at = @At(value = "FIELD", target = "Lnet/minecraft/SharedConstants;isDevelopment:Z")
    )
    private boolean tc$noDev(boolean original) {
        if (TmmConstruct.DEV) return false;
        return original;
    }
}
