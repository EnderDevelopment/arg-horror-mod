package com.ryandossantoscorreia728.arghorrormod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "arghorrormod")
public
class TimerSystem {
    private static long startTime = System.currentTimeMillis();
    private static boolean anomalousEntitySpawned = false;
    private static boolean climaxTriggered = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;

            if (elapsedTime >= 720000 && !anomalousEntitySpawned) {
                AnomalousEntitySystem.spawnAnomalousEntity();
                anomalousEntitySpawned = true;
            }

            if (elapsedTime >= 1200000 && !climaxTriggered) {
                ClimaxSystem.triggerClimax();
                climaxTriggered = true;
            }
        }
    }
}
