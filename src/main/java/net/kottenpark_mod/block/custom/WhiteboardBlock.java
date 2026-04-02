package net.kottenpark_mod.block.custom;

import com.mojang.serialization.MapCodec;
import net.kottenpark_mod.block.entity.ModBlockEntities;
import net.kottenpark_mod.block.entity.custom.WhiteboardBlockEntity;
import net.kottenpark_mod.block.enums.QandA;
import net.kottenpark_mod.state.modproperty.ModProperties;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Tilt;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WhiteboardBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final BooleanProperty SUCCEEDED = ModProperties.SUCCEEDED;
    private static final EnumProperty<QandA> Q_AND_A = ModProperties.Q_AND_A;

    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    public WhiteboardBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ROTATION, 0).with(SUCCEEDED, false).with(Q_AND_A, QandA.AK));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public static final MapCodec<WhiteboardBlock> CODEC = WhiteboardBlock.createCodec(WhiteboardBlock::new);
    @Override
    protected MapCodec<WhiteboardBlock> getCodec() {
        return CODEC;
    }




    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + 180.0F));
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(ROTATION, rotation.rotate(state.get(ROTATION), 16));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.with(ROTATION, mirror.mirror(state.get(ROTATION), 16));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, SUCCEEDED, Q_AND_A);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WhiteboardBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                         PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.getBlockEntity(pos) instanceof WhiteboardBlockEntity whiteboardEntity) {
            if(whiteboardEntity.isEmpty() && !stack.isEmpty()) {
                whiteboardEntity.setStack(0, stack.copyWithCount(1));
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                stack.decrement(1);

                whiteboardEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            } else if(stack.isEmpty() && !player.isSneaking()) {
                ItemStack stackOnPedestal = whiteboardEntity.getStack(0);
                player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                whiteboardEntity.clear();

                whiteboardEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            } else if(player.isSneaking() && !world.isClient()) {

                player.openHandledScreen(whiteboardEntity);
            }
        }

        return ActionResult.SUCCESS;
    }

}
