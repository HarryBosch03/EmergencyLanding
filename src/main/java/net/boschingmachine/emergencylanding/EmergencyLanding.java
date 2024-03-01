package net.boschingmachine.emergencylanding;

import net.boschingmachine.emergencylanding.blocks.BlockRegister;
import net.boschingmachine.emergencylanding.effects.EffectsRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.IOException;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EmergencyLanding.ModID)
public class EmergencyLanding
{
    public static final String ModID = "bmel";

    public EmergencyLanding()
    {
        BlockRegister.Register();
        EffectsRegistry.Register();
    }
}
