package io.github.simplycmd.vanillabean.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    @Inject(at = @At("HEAD"), method = "update")
    public void update(PlayerEntity player, CallbackInfo ci) {
        var self = ((HungerManager) (Object) this);
        player.heal(self.getFoodLevel() - 5);
        self.setFoodLevel(5);
    }
}
