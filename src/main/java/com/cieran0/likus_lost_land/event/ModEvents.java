package com.cieran0.likus_lost_land.event;

import com.cieran0.likus_lost_land.LikusLostLand;
import com.cieran0.likus_lost_land.blocks.ModBlocks;
import com.cieran0.likus_lost_land.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = LikusLostLand.MOD_ID)
public class ModEvents {

    private static final boolean TESTING = true;


    private static final Set<UUID> playersWithItem = new HashSet<>();


    @SubscribeEvent
    public static void onWorldCreateSpawnPosition(LevelEvent.CreateSpawnPosition event) {
        if (!event.getLevel().isClientSide()) {
            BlockPos pos = new BlockPos(0, 0, 0);
            BlockState blockState = ModBlocks.LIKU_HEAD.get().defaultBlockState();
            event.getLevel().setBlock(pos, blockState, 3);
        }
    }
    @SubscribeEvent
    public static void everyTick(TickEvent.PlayerTickEvent event) {

        Player p = event.player;
        ItemStack inHand = p.getInventory().getSelected();
        Item itemInHand = inHand.getItem();
        ResourceLocation name = ForgeRegistries.ITEMS.getKey(itemInHand);
        BlockPos pos = p.getOnPos();
        Level level = p.level();


        boolean isHoldingItem = itemInHand != null && isFromModAndIsGlove(itemInHand);


        if (isHoldingItem && !playersWithItem.contains(p.getUUID())) {
            // The player has just started holding the item, play the sound
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.PINK_PANTHER.get(), SoundSource.PLAYERS, 1.0f, 1.0f, 0);
            playersWithItem.add(p.getUUID());
        } else if (!isHoldingItem && playersWithItem.contains(p.getUUID())) {
            // The player has just stopped holding the item, play a silent sound to stop the previous sound
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.SILENCE.get(), SoundSource.PLAYERS, 0.1f, 1.0f, 0);
            playersWithItem.remove(p.getUUID());
        }
    }

    private static boolean isFromModAndIsGlove(Item item) {
        String namespace = TESTING? "minecraft" : "theft";
        String path = TESTING? "diamond" : "glove";
        ResourceLocation registryName = ForgeRegistries.ITEMS.getKey(item);
        // Check if the item is from the mod "theft" and if it's a glove
        return registryName != null && registryName.getNamespace().equals(namespace) && registryName.getPath().equals(path);
    }

}
