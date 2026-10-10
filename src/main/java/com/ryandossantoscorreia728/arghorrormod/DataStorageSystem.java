package com.ryandossantoscorreia728.arghorrormod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Mod.EventBusSubscriber(modid = "arghorrormod")
public
class DataStorageSystem {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File DATA_FILE = new File("arghorrormod_data.json");

    public static void saveData(long startTime, boolean anomalousEntitySpawned, boolean climaxTriggered) {
        Data data = new Data(startTime, anomalousEntitySpawned, climaxTriggered);
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Data loadData() {
        if (DATA_FILE.exists()) {
            try (FileReader reader = new FileReader(DATA_FILE)) {
                return GSON.fromJson(reader, Data.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Data(System.currentTimeMillis(), false, false);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            long currentTime = System.currentTimeMillis();
            if (currentTime % 5000 == 0) {
                saveData(TimerSystem.getStartTime(), TimerSystem.isAnomalousEntitySpawned(), TimerSystem.isClimaxTriggered());
            }
        }
    }

    public static
    class Data {
        private long startTime;
        private boolean anomalousEntitySpawned;
        private boolean climaxTriggered;

        public Data(long startTime, boolean anomalousEntitySpawned, boolean climaxTriggered) {
            this.startTime = startTime;
            this.anomalousEntitySpawned = anomalousEntitySpawned;
            this.climaxTriggered = climaxTriggered;
        }

        public long getStartTime() {
            return startTime;
        }

        public boolean isAnomalousEntitySpawned() {
            return anomalousEntitySpawned;
        }

        public boolean isClimaxTriggered() {
            return climaxTriggered;
        }
    }
}
