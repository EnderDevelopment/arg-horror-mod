package com.ryandossantoscorreia728.arghorrormod;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "arghorrormod")
public
class ClimaxSystem {
    public static void triggerClimax() {
        Minecraft.getInstance().player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, Integer.MAX_VALUE, 255));
        Minecraft.getInstance().player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, Integer.MAX_VALUE, 255));
        Minecraft.getInstance().player.sendMessage(new StringTextComponent("The end is near."), Minecraft.getInstance().player.getUniqueID());
    }
}
