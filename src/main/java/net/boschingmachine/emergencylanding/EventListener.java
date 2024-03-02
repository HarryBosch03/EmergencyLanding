package net.boschingmachine.emergencylanding;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = EmergencyLanding.ModID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventListener
{
    @SubscribeEvent
    public static void OnPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        var key = EmergencyLanding.ModID + ".playerInitialized";
        var playerData = event.getPlayer().getPersistentData();
        //if (playerData.contains(key) && playerData.getBoolean(key)) return;
        playerData.putBoolean(key, true);

        if (event.getPlayer() instanceof ServerPlayer player)
        {
            var spawnX = 0;
            var spawnZ = 0;

            var resourceKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(EmergencyLanding.ModID, "exoplanet"));
            var level = player.getServer().getLevel(resourceKey);

            var spawnY = level.getMinBuildHeight();
            var spawnYMax = level.getMaxBuildHeight();

            while (spawnY < spawnYMax)
            {
                if (level.getBlockState(new BlockPos(spawnX, spawnY, spawnZ)).isAir()) break;
                spawnY++;
            }
//
//            player.changeDimension(level);
//            player.setPos(spawnX, spawnY, spawnZ);
//
            player.setRespawnPosition(resourceKey, new BlockPos(spawnX, spawnY, spawnZ), 0.0f,true, false);
//
//            player.getInventory().clearContent();
//            player.getInventory().add(new ItemStack(Items.APPLE, 16));
        }
    }
}
