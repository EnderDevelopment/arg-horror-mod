package com.ryandossantoscorreia728.arghorrormod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "arghorrormod")
public
class ChatMessageSystem {
    private static final String[] MESSAGES = {
        "You feel a cold presence watching you.",
        "Something is whispering your name.",
        "The walls seem to breathe.",
        "You hear footsteps behind you.",
        "The air grows colder."
    };

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            long currentTime = System.currentTimeMillis();
            if (currentTime % 300000 == 0) {
                int randomIndex = (int) (Math.random() * MESSAGES.length);
                Minecraft.getInstance().player.sendMessage(new StringTextComponent(MESSAGES[randomIndex]), Minecraft.getInstance().player.getUniqueID());
            }
        }
    }
}
