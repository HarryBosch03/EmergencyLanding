package net.boschingmachine.emergencylanding.blocks.Nameplate;

import net.boschingmachine.emergencylanding.blockEntities.BlockEntityRegister;
import net.boschingmachine.emergencylanding.blocks.BlockRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class NameplateBlock extends Block implements EntityBlock
{
    public NameplateBlock(Properties blockProperties)
    {
        super(blockProperties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random rng)
    {
        var direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        var other = pos.relative(direction.getOpposite());
        var anchored = level.getBlockState(other);
        if (anchored.isAir() || !anchored.isCollisionShapeFullBlock(level, other))
        {
            level.setBlockAndUpdate(pos, null);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        var direction = ctx.getClickedFace();
        if (direction == Direction.UP || direction == Direction.DOWN) return null;

        var pos = ctx.getClickedPos().relative(ctx.getClickedFace().getOpposite());
        var other = ctx.getLevel().getBlockState(pos);
        if (!other.isCollisionShapeFullBlock(ctx.getLevel(), ctx.getClickedPos())) return null;

        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx)
    {
        var direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        switch (direction)
        {
            case NORTH -> {
                return Block.box(0, 4, 14, 16, 12, 16);
            }
            case SOUTH -> {
                return Block.box(0, 4, 0, 16, 12, 2);
            }
            case WEST -> {
                return Block.box(14, 4, 0, 16, 12, 16);
            }
            case EAST -> {
                return Block.box(0, 4, 0, 2, 12, 16);
            }
        }
        return super.getShape(state, getter, pos, ctx);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return BlockEntityRegister.NameplateTile.get().create(pos, state);
    }
}
