package net.boschingmachine.emergencylanding.blockEntities;

import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.boschingmachine.emergencylanding.blocks.BlockRegister;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegister
{
    public static final DeferredRegister<BlockEntityType<?>> Entities = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EmergencyLanding.ModID);

    public static void Register()
    {
        Entities.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}