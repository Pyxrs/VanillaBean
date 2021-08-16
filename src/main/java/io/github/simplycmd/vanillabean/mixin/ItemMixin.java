package io.github.simplycmd.vanillabean.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

@Mixin(Item.class)
public class ItemMixin {
    @Mutable
    @Shadow
    @Final
    private int maxCount;

    @Shadow
    @Final
    private FoodComponent foodComponent;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(Item.Settings settings, CallbackInfo ci) {
        if (foodComponent != null)
            maxCount = 1;
	}
}
