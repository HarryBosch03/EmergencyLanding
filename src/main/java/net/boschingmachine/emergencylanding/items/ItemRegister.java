package net.boschingmachine.emergencylanding.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

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
}
