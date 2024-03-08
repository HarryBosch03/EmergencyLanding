package net.boschingmachine.emergencylanding.blocks;

import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegister
{
    public static final DeferredRegister<Block> Blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, EmergencyLanding.ModID);

    public static RegistryObject<Block> Duststone = Blocks.register("duststone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f)));
    public static RegistryObject<Block> WallNameplate = Blocks.register("wall_nameplate", () -> new WallSignBlock(BlockBehaviour.Properties.of(Material.METAL), null));
    public static RegistryObject<Block> FloorNameplate = Blocks.register("floor_nameplate", () -> new StandingSignBlock(BlockBehaviour.Properties.of(Material.METAL), null));

    public static void Register()
    {
        Blocks.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
