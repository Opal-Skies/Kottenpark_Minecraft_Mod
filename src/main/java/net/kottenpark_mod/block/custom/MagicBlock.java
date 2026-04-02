package net.kottenpark_mod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagicBlock extends Block {
    public MagicBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                 BlockHitResult hit) {
        world.addParticleClient(ParticleTypes.BUBBLE, pos.getX() + 0.5, pos.getY() + 1,
                pos.getZ() + 0.5, 0, 1, 0);

        world.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1f, 1f);
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof ItemEntity itemEntity) {
            if(itemEntity.getStack().isOf(Items.COAL)) {
                itemEntity.setStack(new ItemStack(Items.COPPER_INGOT, itemEntity.getStack().getCount()));
            } else if(itemEntity.getStack().isOf(Items.COPPER_INGOT)) {
                itemEntity.setStack(new ItemStack(Items.IRON_INGOT, itemEntity.getStack().getCount()));
            } else if(itemEntity.getStack().isOf(Items.IRON_INGOT)) {
                itemEntity.setStack(new ItemStack(Items.EMERALD, itemEntity.getStack().getCount()));
            } else if(itemEntity.getStack().isOf(Items.EMERALD)) {
                itemEntity.setStack(new ItemStack(Items.DIAMOND, itemEntity.getStack().getCount()));
            } else if(itemEntity.getStack().isOf(Items.DIAMOND)) {
                itemEntity.setStack(new ItemStack(Items.NETHERITE_INGOT, itemEntity.getStack().getCount()));
            } else if(itemEntity.getStack().isOf(Items.NETHERITE_INGOT)) {
                itemEntity.setStack(new ItemStack(Items.COAL, itemEntity.getStack().getCount()));
            }
        }


        super.onSteppedOn(world, pos, state, entity);
    }
}
