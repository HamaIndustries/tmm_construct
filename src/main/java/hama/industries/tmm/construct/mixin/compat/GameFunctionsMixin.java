package hama.industries.tmm.construct.mixin.compat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.doctor4t.trainmurdermystery.game.GameFunctions;
import hama.industries.tmm.construct.TmmConstruct;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameFunctions.class)
public class GameFunctionsMixin {
    @ModifyExpressionValue(method = "startGame", at = @At(value = "FIELD", target = "Ldev/doctor4t/trainmurdermystery/api/GameMode;minPlayerCount:I", opcode = Opcodes.GETFIELD), remap = true)
    private static int tc$setToZeroIfDevelopment(int original) {
        if (TmmConstruct.DEV) {
            return 0;
        }
        return original;
    }
}
