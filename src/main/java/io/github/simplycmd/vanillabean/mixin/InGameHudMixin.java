package io.github.simplycmd.vanillabean.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private int y = 0;

    @Redirect(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
    private void drawTexture(InGameHud self, MatrixStack matrices, int x, int y, int u, int v, int width, int height) {
        if (v == 27) {
            this.y = y;
        } else if (v == 18) {
            self.drawTexture(matrices, x, this.y, u, v, width, height);
        } else {
            self.drawTexture(matrices, x, y, u, v, width, height);
        }
    }
}
