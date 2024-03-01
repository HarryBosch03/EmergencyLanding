package net.boschingmachine.emergencylanding.mixin;

import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.boschingmachine.emergencylanding.effects.EffectsRegistry;
import net.boschingmachine.emergencylanding.extras.IHasTemperature;
import net.boschingmachine.emergencylanding.extras.TemperatureHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixins extends LivingEntity implements IHasTemperature
{
    @Unique
    public TemperatureHandler temperature = new TemperatureHandler();

    protected PlayerMixins(EntityType<? extends LivingEntity> type, Level level)
    {
        super(type, level);
    }

    @Inject(method = "readAdditionalSaveData", at = @At(value="TAIL"))
    public void OnReadAdditionalSaveData(CompoundTag nbt, CallbackInfo info)
    {
        temperature.LoadData(nbt);
    }

    @Inject(method = "addAdditionalSaveData", at = @At(value="TAIL"))
    public void OnAddAdditionalSaveData(CompoundTag nbt, CallbackInfo info)
    {
        temperature.SaveData(nbt);
    }

    @Inject(method = "tick", at = @At(value="TAIL"))
    public void OnTick(CallbackInfo info)
    {
        var player = (Player)(Object)this;
        temperature.Tick(player);

        if (player.hasEffect(EffectsRegistry.Heatstroke.get()))
        {

        }
    }

    @Override
    public float GetCurrentTemperature()
    {
        return temperature.currentTemperature;
    }

    @Override
    public float GetTargetTemperature()
    {
        return temperature.targetTemperature;
    }
}
