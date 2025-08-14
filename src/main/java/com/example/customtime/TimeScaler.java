package com.example.customtime;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.HashMap;
import java.util.Map;
public class TimeScaler {
    private static final Map<Level, Double> FRACTIONAL = new HashMap<>();

    public static void onLevelLoad(LevelEvent.Load evt){
        if (evt.getLevel() instanceof ServerLevel sl){
            FRACTIONAL.put(sl, 0.0);
            if(ModTimeConfig.freezeGamerule()){
                sl.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, sl.getServer());
            }
        }
    }

    public static void onLevelUnload(LevelEvent.Unload evt){
        if(evt.getLevel() instanceof ServerLevel sl) FRACTIONAL.remove(sl);
    }

    public static void onLevelTick(LevelTickEvent.Post evt){
        if(!(evt.getLevel() instanceof ServerLevel sl)) return;
        if (ModTimeConfig.overworldOnly() && sl.dimension() != Level.OVERWORLD) return;
        if (sl.getGameRules().getBoolean(net.minecraft.world.level.GameRules.RULE_DAYLIGHT)) return; // vanilla advancing

        long dayTime = sl.getDayTime();
        long tod = Math.floorMod(dayTime, 2400L);
        boolean isDay = tod <1200L;

        double scale = isDay ? ModTimeConfig.dayScale() : ModTimeConfig.nightScale();
        if(scale <= 0.0) return; //pause segment

        double add = 1.0 / scale; //vanilla would be +1 per tick
        double acc = FRACTIONAL.getOrDefault(sl, 0.0) + add;
        int whole = (int) Math.floor(acc);
        FRACTIONAL.put(sl, acc - whole);
        if (whole > 0) sl.setDayTime(dayTime + whole);




    }



}
