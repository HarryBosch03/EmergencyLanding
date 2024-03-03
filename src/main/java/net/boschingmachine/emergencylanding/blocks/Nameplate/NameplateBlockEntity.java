package net.boschingmachine.emergencylanding.blocks.Nameplate;

import net.boschingmachine.emergencylanding.blockEntities.BlockEntityRegister;
import net.boschingmachine.emergencylanding.blocks.BlockRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NameplateBlockEntity extends BlockEntity
{
    public NameplateBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.NameplateTile.get(), pos, state);
    }
}
