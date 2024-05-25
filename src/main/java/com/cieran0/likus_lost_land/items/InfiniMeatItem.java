package com.cieran0.likus_lost_land.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class InfiniMeatItem extends Item {
    public InfiniMeatItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            // Restore the item stack after usage
            player.getFoodData().eat(pStack.getItem(), pStack);
            player.getCooldowns().addCooldown(this, 20);
        }

        // Return the same item stack instead of creating a new one or reducing it
        return pStack;
    }
}
