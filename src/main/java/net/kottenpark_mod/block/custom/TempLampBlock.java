package net.kottenpark_mod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TempLampBlock extends Block {
    public static final IntProperty TEXTURE_STATE = IntProperty.of("texture_state",0,5);
    public TempLampBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(TEXTURE_STATE,0));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient()){
            world.setBlockState(pos, state.cycle(TEXTURE_STATE));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TEXTURE_STATE);
    }
}
