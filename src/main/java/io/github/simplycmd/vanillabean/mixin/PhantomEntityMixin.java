package io.github.simplycmd.vanillabean.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.sound.SoundEvent;

@Mixin(PhantomEntity.class)
public class PhantomEntityMixin {
    @Overwrite
    public SoundEvent getAmbientSound() {
        return null;
    }
}
