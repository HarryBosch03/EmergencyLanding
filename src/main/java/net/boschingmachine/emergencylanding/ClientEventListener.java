package net.boschingmachine.emergencylanding;

import net.boschingmachine.emergencylanding.blockEntities.BlockEntityRegister;
import net.boschingmachine.emergencylanding.blocks.BlockRegister;
import net.boschingmachine.emergencylanding.blocks.Nameplate.NameplateRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmergencyLanding.ModID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventListener
{
    @SubscribeEvent
    public static void RegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(BlockEntityRegister.NameplateTile.get(), NameplateRenderer::new);
    }
}
