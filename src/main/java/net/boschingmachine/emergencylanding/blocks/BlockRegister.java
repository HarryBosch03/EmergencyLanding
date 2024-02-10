package net.boschingmachine.emergencylanding.blocks;

import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.boschingmachine.emergencylanding.items.ItemRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegister
{
    public static final DeferredRegister<Block> Blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, EmergencyLanding.ModID);
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, EmergencyLanding.ModID);

    public static void Register()
    {
        SimpleBlock("duststone", BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f));

        Blocks.register(FMLJavaModLoadingContext.get().getModEventBus());
        Items.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private static void SimpleBlock(String identifier, BlockBehaviour.Properties properties)
    {
        var block = Blocks.register(identifier, () -> new Block(properties));
        Items.register(identifier, () -> new BlockItem(block.get(), new Item.Properties().tab(ItemRegister.CreativeTab)));
    }
}
