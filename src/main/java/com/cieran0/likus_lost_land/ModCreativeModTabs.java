package com.cieran0.likus_lost_land;

import com.cieran0.likus_lost_land.blocks.ModBlocks;
import com.cieran0.likus_lost_land.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LikusLostLand.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LIKU_TAB = CREATIVE_MODE_TABS.register("liku_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LIKU_HEAD.get()))
                    .title(Component.translatable("creativetab.liku_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        //output.accept(ModItems.INFINI_MEAT.get());
                        //output.accept(ModBlocks.LIKU_STONE.get());
                        output.accept(ModBlocks.LIKU_HEAD.get());
                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
