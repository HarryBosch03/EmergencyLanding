package net.boschingmachine.emergencylanding.mixin;

import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerList.class)
public abstract class PlayerRespawnMixin
{
    @Redirect(method = "respawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;overworld()Lnet/minecraft/server/level/ServerLevel;"))
    public ServerLevel RedirectRespawnDim(MinecraftServer instance)
    {
        return instance.getLevel(EmergencyLanding.Exoplanet());
    }

    @Redirect(method = "getPlayerForLogin", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;overworld()Lnet/minecraft/server/level/ServerLevel;"))
    public ServerLevel RedirectConstructorDim(MinecraftServer instance)
    {
        return instance.getLevel(EmergencyLanding.Exoplanet());
    }

    @Redirect(method = "placeNewPlayer", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;OVERWORLD:Lnet/minecraft/resources/ResourceKey;"))
    public ResourceKey<Level> RespawnPlacementDim()
    {
        return EmergencyLanding.Exoplanet();
    }
}
