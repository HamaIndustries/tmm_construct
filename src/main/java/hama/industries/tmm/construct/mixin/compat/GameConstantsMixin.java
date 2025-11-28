package hama.industries.tmm.construct.mixin.compat;

import dev.doctor4t.trainmurdermystery.game.GameConstants;
import dev.doctor4t.trainmurdermystery.util.ShopEntry;
import hama.industries.tmm.construct.TmmConstruct;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Map;

@Mixin(GameConstants.class)
public interface GameConstantsMixin {

    @Shadow
    public static int getInTicks(int minutes, int seconds) {
        return 0;
    }

    @Shadow
    Map<Item, Integer> ITEM_COOLDOWNS = null;

    @Inject(
            method = "init",
            at = @At("TAIL")
    )
    private static void tc$after(CallbackInfo ci) {
        ITEM_COOLDOWNS.put(TmmConstruct.THETISCOPE, getInTicks(4, 0));
    }

    @Inject(
            method = "lambda$static$0",
            at = @At("TAIL")
    )
    private static void tc$entries(ArrayList<ShopEntry> entries, CallbackInfo ci) {
        entries.add(new ShopEntry(TmmConstruct.THETISCOPE.getDefaultStack(), 250, ShopEntry.Type.TOOL) {
            @Override
            public boolean onBuy(@NotNull PlayerEntity player) {
                return TmmConstruct.Keys.MASQUERADE.get(player.getWorld()).waltz();
            }
        });
    }
}
