package io.github.simplycmd.vanillabean.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirtPathBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin extends HostileEntity {
    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.getMaterial().isLiquid()) {
            BlockState blockState = this.world.getBlockState(pos.up());
            BlockSoundGroup blockSoundGroup = blockState.isIn(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
                    ? blockState.getSoundGroup()
                    : state.getSoundGroup();
            this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.4F,
                    blockSoundGroup.getPitch());
        }
    }

    @Redirect(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"))
    private Explosion createExplosion(World world, Entity entity, double x, double y, double z, float power, Explosion.DestructionType destructionType) {
        Explosion explosion = new Explosion(world, entity, x, y, z, power, false, DestructionType.NONE);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(true);
        for (var pos : explosion.getAffectedBlocks()) {
            if (world.getBlockState(pos).getBlock() instanceof GrassBlock || world.getBlockState(pos).getBlock() instanceof DirtPathBlock || world.getBlockState(pos).getBlock() instanceof FarmlandBlock)
                world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            else if (world.getBlockState(pos).getBlock() instanceof PlantBlock)
                world.breakBlock(pos, true);
        }
        return explosion;
    }
}
