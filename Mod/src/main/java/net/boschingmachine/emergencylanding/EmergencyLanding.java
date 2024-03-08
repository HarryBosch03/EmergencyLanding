package net.boschingmachine.emergencylanding;

import net.boschingmachine.emergencylanding.blockEntities.BlockEntityRegister;
import net.boschingmachine.emergencylanding.blocks.BlockRegister;
import net.boschingmachine.emergencylanding.items.ItemRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EmergencyLanding.ModID)
public class EmergencyLanding
{
    public static final String ModID = "bmel";

    public static ResourceKey<Level> Exoplanet()
    {
        return ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(EmergencyLanding.ModID, "exoplanet"));
    }

    public EmergencyLanding()
    {
        BlockRegister.Register();
        BlockEntityRegister.Register();
        ItemRegister.Register();
    }
}
