package hama.industries.tmm.construct.mixin.compat.hater_laser;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.command.argument.ArgumentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArgumentTypes.class)
public class ArgumentLaser {
    @ModifyExpressionValue(
            method = "register(Lnet/minecraft/registry/Registry;)Lnet/minecraft/command/argument/serialize/ArgumentSerializer;"
            , at = @At(value = "FIELD", target = "Lnet/minecraft/SharedConstants;isDevelopment:Z")
    )
    private static boolean tc$noDev(boolean original) {
        if (TmmConstruct.DEV) return false;
        return original;
    }
}
