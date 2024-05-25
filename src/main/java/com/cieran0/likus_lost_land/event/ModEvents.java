package com.cieran0.likus_lost_land.event;

import com.cieran0.likus_lost_land.LikusLostLand;
import com.cieran0.likus_lost_land.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LikusLostLand.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onWorldCreateSpawnPosition(LevelEvent.CreateSpawnPosition event) {
        if (!event.getLevel().isClientSide()) {
            BlockPos pos = new BlockPos(0, 0, 0);
            BlockState blockState = ModBlocks.LIKU_HEAD.get().defaultBlockState();
            event.getLevel().setBlock(pos, blockState, 3);
        }
    }

}
