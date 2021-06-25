package io.github.simplycmd.vanillabean_bundle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Client implements ClientModInitializer {
    public static KeyBinding zoomKey;

    @Override
    public void onInitializeClient() {
        zoomKey = new KeyBinding("key." + Main.MOD_ID + ".zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category." + Main.MOD_ID + ".zoom");
        KeyBindingHelper.registerKeyBinding(zoomKey);
    }
}
