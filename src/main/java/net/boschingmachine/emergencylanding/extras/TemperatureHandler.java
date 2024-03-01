package net.boschingmachine.emergencylanding.extras;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ObjectHolder;

public class TemperatureHandler
{
    public static final float OutsideMin = 18.0f;
    public static final float OutsideMax = 45.0f;
    public static final float InsideTemp = 24.0f;
    public static final float InsideInfluence = 0.6f;
    public static final float BaseTransferSpeed = 0.2f;
    public static final float MinSafeTemp = 15.0f;
    public static final float MaxSafeTemp = 35.0f;

    @ObjectHolder("bmel:heatstroke")
    public static MobEffect heatstrokeEffect;

    public static final String TemperatureKey = "current_temperature";
    public static final String UseTemperatureKey = "use_temperature";

    public float currentTemperature = 37.0f;
    public float targetTemperature = 37.0f;
    public int heatstrokeCounter;
    public int hypothermiaCounter;
    public float Conductivity = 1.0f;

    public void SaveData(CompoundTag tag)
    {
        tag.putBoolean(UseTemperatureKey, true);
        tag.putFloat(TemperatureKey, currentTemperature);
    }

    public void LoadData(CompoundTag tag)
    {
        if (tag.contains(UseTemperatureKey) && tag.getBoolean(UseTemperatureKey))
        {
            currentTemperature = tag.getFloat(TemperatureKey);
        }
    }

    public void Tick(Player player)
    {
        Conductivity = 1.0f;
        CalculateTargetTemperature(player);
        var transferSpeed = BaseTransferSpeed * Conductivity;
        currentTemperature += (targetTemperature - currentTemperature) * transferSpeed / 20.0f;

        if (currentTemperature > MaxSafeTemp) heatstrokeCounter++;
        else if (heatstrokeCounter > 0) heatstrokeCounter--;

        if (heatstrokeCounter > 400)
        {
            player.addEffect(new MobEffectInstance(heatstrokeEffect, heatstrokeCounter, heatstrokeCounter / 400 - 1));
        }

        if (currentTemperature < MinSafeTemp) hypothermiaCounter++;
        else if (hypothermiaCounter > 0) hypothermiaCounter--;

        player.displayClientMessage(new TextComponent("Target Temperature: " + ((int) (targetTemperature * 10.0f) / 10.0f) + " | Current Temperature: " + ((int) (currentTemperature * 10.0f) / 10.0f)), true);
    }

    public void CalculateTargetTemperature(Player player)
    {
        var level = player.getLevel();
        var position = player.position();
        var blockPosition = new BlockPos(position);

        var time = level.getDayTime();
        var sunExposure = (float) Math.pow(Mth.cos(Mth.PI * (time - 6000) / 12000.0f) * 0.5f + 0.5f, 1.5f);

        var outsideTemperature = sunExposure * (OutsideMax - OutsideMin) + OutsideMin;
        var insideTemperature = InsideTemp * InsideInfluence + outsideTemperature * (1.0f - InsideInfluence);

        targetTemperature = level.canSeeSky(blockPosition) ? outsideTemperature : insideTemperature;

        if (player.isInWater())
        {
            targetTemperature = insideTemperature;
            Conductivity *= 10.0f;
        }
    }
}