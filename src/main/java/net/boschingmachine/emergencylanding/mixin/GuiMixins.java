package net.boschingmachine.emergencylanding.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.boschingmachine.emergencylanding.extras.IHasTemperature;
import net.boschingmachine.emergencylanding.extras.TemperatureHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixins extends GuiComponent
{
    @Shadow @Final protected Minecraft minecraft;

    @Inject(method = "renderHearts", at = @At(value = "TAIL"))
    public void OnRender(PoseStack stack, Player player, int xStart, int yStart, int p_168693_, int p_168694_, float p_168695_, int p_168696_, int p_168697_, int p_168698_, boolean p_168699_, CallbackInfo ci)
    {
        DrawTemperature(stack, player, xStart, yStart);
    }

    private void DrawTemperature(PoseStack stack, Player player, int xStart, int yStart)
    {
        var currentTemperature = ((IHasTemperature) player).GetCurrentTemperature();
        var targetTemperature = ((IHasTemperature) player).GetTargetTemperature();

        var time = player.level.getGameTime();
        //currentTemperature = Mth.lerp(Mth.sin(time * 0.04f) * 0.5f + 0.5f, TemperatureHandler.MinSafeTemp, TemperatureHandler.MaxSafeTemp);
        //targetTemperature = Mth.lerp(Mth.cos(time * 0.04f) * 0.5f + 0.5f, TemperatureHandler.MinSafeTemp, TemperatureHandler.MaxSafeTemp);

        var icons = new ResourceLocation(EmergencyLanding.ModID, "textures/gui/icons.png");
        RenderSystem.setShaderTexture(0, icons);

        xStart += -83 - 3;

        blit(stack, xStart, yStart, 0, 10, 83, 9);
        blit(stack, xStart + TemperatureToXOffset(currentTemperature), yStart - 1, 83, 9, 7, 6);
        blit(stack, xStart + TemperatureToXOffset(targetTemperature), yStart + 5, 83, 15, 7, 4);
    }

    @Unique
    public int TemperatureToXOffset(float t)
    {
        var tn = (t - TemperatureHandler.MinSafeTemp) / (TemperatureHandler.MaxSafeTemp - TemperatureHandler.MinSafeTemp);
        if (tn < 0.0f) tn = 0.0f;
        if (tn > 1.0f) tn = 1.0f;
        return (int)(tn * 83.0f - 3.5f);
    }
}
