package net.boschingmachine.emergencylanding.blocks.Nameplate;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class NameplateRenderer implements BlockEntityRenderer<NameplateBlockEntity>
{
    private final BlockEntityRendererProvider.Context context;

    public NameplateRenderer(BlockEntityRendererProvider.Context context)
    {
        this.context = context;
    }

    @Override
    public void render(NameplateBlockEntity nameplateBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1)
    {
        var dispatcher = context.getBlockRenderDispatcher();
        dispatcher.renderSingleBlock(nameplateBlockEntity.getBlockState(), poseStack, multiBufferSource, i, i1);
    }
}
