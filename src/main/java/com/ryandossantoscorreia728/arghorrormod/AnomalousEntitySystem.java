package com.ryandossantoscorreia728.arghorrormod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "arghorrormod")
public
class AnomalousEntitySystem {
    private static ArmorStandEntity[] anomalousEntities = new ArmorStandEntity[6];

    public static void spawnAnomalousEntity() {
        World world = Minecraft.getInstance().world;
        if (world != null) {
            BlockPos playerPos = Minecraft.getInstance().player.getPosition();
            for (int i = 0; i < 6; i++) {
                anomalousEntities[i] = new ArmorStandEntity(world, playerPos.getX() + i, playerPos.getY(), playerPos.getZ() + i);
                anomalousEntities[i].setInvisible(true);
                anomalousEntities[i].setHelmet(new ItemStack(Items.OBSIDIAN));
                world.addEntity(anomalousEntities[i]);
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (ArmorStandEntity entity : anomalousEntities) {
                if (entity != null && entity.isAlive()) {
                    if (Minecraft.getInstance().player.getDistance(entity) < 5) {
                        entity.remove();
                    }
                }
            }
        }
    }
}
