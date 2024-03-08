package net.boschingmachine.emergencylanding.items;

import net.boschingmachine.emergencylanding.EmergencyLanding;
import net.boschingmachine.emergencylanding.blocks.BlockRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegister
{
    public static final CreativeModeTab CreativeTab = new CreativeModeTab("bmel")
    {
        @ObjectHolder("bmel:duststone")
        public static final Item iconItem = null;

        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(iconItem);
        }
    };

    public static final Item.Properties defaultProperties = new Item.Properties().tab(CreativeTab);

    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, EmergencyLanding.ModID);

    public static final RegistryObject<Item> Duststone = Items.register("duststone", () -> new BlockItem(BlockRegister.Duststone.get(), defaultProperties));
    public static final RegistryObject<Item> Nameplate = Items.register("nameplate", () -> new BlockItem(BlockRegister.Nameplate.get(), defaultProperties));

    public static void Register()
    {
        Items.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
