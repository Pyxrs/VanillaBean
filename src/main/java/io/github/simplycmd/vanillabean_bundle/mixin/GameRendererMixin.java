package io.github.simplycmd.vanillabean_bundle.mixin;

import io.github.simplycmd.vanillabean_bundle.Client;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    boolean zoomLastTick = false;

    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> cir) {
        if (Client.zoomKey.wasPressed()) {
            cir.setReturnValue(19.0);
            MinecraftClient.getInstance().options.smoothCameraEnabled = true;
            zoomLastTick = true;
        } else {
            if (zoomLastTick) {
                MinecraftClient.getInstance().options.smoothCameraEnabled = false;
                zoomLastTick = false;
            }
        }
    }
}