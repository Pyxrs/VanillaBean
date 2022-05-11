package io.github.simplycmd.vanillabean.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.FoodComponent;

@Mixin(FoodComponent.class)
public class FoodComponentMixin {
    @Final
    @Shadow
    private final boolean snack = true;
}
