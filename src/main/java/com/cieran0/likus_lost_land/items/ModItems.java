package com.cieran0.likus_lost_land.items;

import com.cieran0.likus_lost_land.LikusLostLand;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LikusLostLand.MOD_ID);

//    public static final RegistryObject<Item> INFINI_MEAT = ITEMS.register("infini_meat", () -> new InfiniMeatItem(new Item.Properties()
//            .stacksTo(1)
//            .food(new FoodProperties.Builder()
//                    .nutrition(8)
//                    .saturationMod(2f)
//                    .meat()
//                    .build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
