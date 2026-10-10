package com.ryandossantoscorreia728.arghorrormod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "arghorrormod")
public
class BlackScreenSystem {
    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            if (Minecraft.getInstance().gameSettings.showDebugInfo) {
                Minecraft.getInstance().getMainWindow().setTitle("ARG Horror Mod");
            } else {
                Minecraft.getInstance().getMainWindow().setTitle("");
            }
        }
    }
}
