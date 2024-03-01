package net.boschingmachine.emergencylanding.effects;

import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsRegistry
{
    public static final DeferredRegister<MobEffect> register = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EmergencyLanding.ModID);

    public static RegistryObject<MobEffect> Heatstroke;

    public static void Register()
    {
        Heatstroke = register.register("heatstroke", Heatstroke::new);

        register.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
